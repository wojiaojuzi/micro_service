package edge.node.service;


import com.alibaba.fastjson.JSON;
import edge.node.mapper.NodeMapper;
import edge.node.mapper.ServiceMapper;
import edge.node.model.*;
import edge.node.model.api.LogFeign;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class NodeService {
    private static final String mysqlSdfPatternString = "yyyy-MM-dd HH:mm:ss";
    private final NodeMapper nodeMapper;
    private final ServiceMapper serviceMapper;
    private final LogFeign logFeign;
    private static final Logger logger = LogManager.getLogger(NodeService.class);

    public NodeService(NodeMapper nodeMapper, LogFeign logFeign, ServiceMapper serviceMapper){
        this.nodeMapper = nodeMapper;
        this.logFeign = logFeign;
        this.serviceMapper = serviceMapper;
    }

    //节点注册逻辑要求改
    public Node nodeRegister(Node node,String account){
        Date createTime = new Date();
        SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
        node.setNodeCreateAt(mysqlSdf.format(createTime));

        //由Ip获取地理位置
        //RestTemplate restTmpl = new RestTemplate();
        //String url = "http://freeapi.ipip.net/"+node.getIp();
        //String str = restTmpl.getForObject(url, String.class);
        //List<String> list = JSON.parseArray(str, String.class);
        if(isPing(node.getIp())) {
            LocBody loc = getLocByIpApi(node.getIp());
            //LocBody loc = getLocByGD(node.getIp());
            node.setLocation(loc.getCity());
            node.setLon(loc.getLon());
            node.setLat(loc.getLat());
            nodeMapper.create_node(node.getNodeName(), node.getLocation(), node.getLon(),
                    node.getLat(), node.getNodeStatus(), node.getNodeCreateAt(),
                    node.getRunAt(), node.getEndLastAt(),
                    node.getCpu(), 0, node.getMemory(), 0, node.getIp(),
                    node.getRemark());
            serviceMapper.createImage(node.getNodeName(),null,null,"registry.cn-hangzhou.aliyuncs.com/edge_node/image_system:eureka",false);
            serviceMapper.createImage(node.getNodeName(),null,null,"mysql:latest",false);
            Node test = nodeMapper.getNodeByNodeName(node.getNodeName());
            if (test == null)
                return null;
            else {
                logFeign.addLog(account, "注册节点:  节点名=" + node.getNodeName() + " , 位置=" + node.getLocation());
                return test;
            }
        }
        System.out.println("ip不可用");
        return null;
    }

    public static boolean isPing(String ip) {
        logger.debug("ip地址为："+ip);
        if (ip == null){
            return false;
        }
        BufferedReader in = null;
        try {
            Process pro = Runtime.getRuntime().exec("ping " + ip +" -n 5 -w 5000");
            //GB2312  解决InputStreamReader乱码问题
            in = new BufferedReader(new InputStreamReader(pro.getInputStream(),"GB2312"));
            //逐行检查输出，计算类似出现=23ms TTL=62字样的次数
            int connectedCount = 0;
            String line = null;
            while ((line = in.readLine()) != null){
                logger.info(line);
                connectedCount += getCheckResult(line);// 如果出现类似=23ms TTL=62这样的字样,出现的次数=测试次数则返回真
            }
            return connectedCount == 5;
        } catch (Exception ex) {
            ex.printStackTrace();   // 出现异常则返回假
            return false;
        }finally {
            try {
                in.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    //若line含有=18ms TTL=16字样,说明已经ping通,返回1,否則返回0.
    private static int getCheckResult(String line) {  // System.out.println("控制台输出的结果为:"+line);
        Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)",  Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            return 1;
        }
        return 0;
    }

    //删除节点逻辑要修改
    public void nodeDelete(String node_name,String account){
        nodeMapper.deleteNodeByNodeName(node_name);
        logFeign.addLog(account,"删除节点:  节点名="+node_name);
    }

    public Node nodeGet(String node_name){ return nodeMapper.getNodeByNodeName(node_name);}

    public int get_on_num(){
        return nodeMapper.get_on_num().size();
    }

    public List<Node> get_all(){ return nodeMapper.get_all(); }

    public List<return_location> get_all_location(){
        List<Node> list = nodeMapper.get_all();
        List<return_location> ans = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            return_location p = new return_location();
            p.setLocation(list.get(i).getLocation());
            p.setLon(list.get(i).getLon());
            p.setLat(list.get(i).getLat());
            p.setValue(20);
            ans.add(p);
        }
        return ans;
    }

    public List<return_location> get_on_location(){
        List<Node> list = nodeMapper.get_on();
        List<return_location> ans = new ArrayList<>();
        for(int i=0;i<list.size();i++) {
            return_location p = new return_location();
            p.setLocation(list.get(i).getLocation());
            p.setLon(list.get(i).getLon());
            p.setLat(list.get(i).getLat());
            p.setValue(20);
            ans.add(p);
        }
        return ans;
    }

    public List<return_location> get_off_location(){
        List<Node> list = nodeMapper.get_off();
        List<return_location> ans = new ArrayList<>();
        for(int i=0;i<list.size();i++) {
            return_location p = new return_location();
            p.setLocation(list.get(i).getLocation());
            p.setLon(list.get(i).getLon());
            p.setLat(list.get(i).getLat());
            p.setValue(20);
            ans.add(p);
        }
        return ans;
    }

    public boolean deploy(String node_name,String account) throws IOException {
      /*  boolean ans = nodeMapper.geNodeStatusByNodeName(node_name);
        if(ans == true){
            return false;
        }
        else {
            String id = nodeMapper.getNodeByNodeName(node_name).getId();
            File dir = new File("D:\\micro_service\\deploy");
            System.out.println(dir+" open.py "+id);
            Runtime.getRuntime().exec("python open.py "+id,null,dir);
            Date createTime = new Date();
            SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
            nodeMapper.updateNodeStatusByNodeName(true, node_name);
            nodeMapper.updateRunAtByNodeName(mysqlSdf.format(createTime),node_name);

            logFeign.addLog(account,"微服务部署:  节点名="+node_name);
            return true;
        }*/
      return true;
    }

    public boolean close(String node_name,String account) throws IOException {
       /* boolean ans = nodeMapper.geNodeStatusByNodeName(node_name);
        if(ans == false){
            return false;
        }
        else {
            String id = nodeMapper.getNodeByNodeName(node_name).getId();
            File dir = new File("D:\\micro_service\\deploy");
            System.out.println(dir+" close.py "+id);
            Runtime.getRuntime().exec("python close.py "+id,null,dir);
            Date createTime = new Date();
            SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
            nodeMapper.updateNodeStatusByNodeName(false, node_name);
            nodeMapper.updateEndLastAtByNodeName(mysqlSdf.format(createTime),node_name);
            nodeMapper.updateRunAtByNodeName("",node_name);

            logFeign.addLog(account,"取消微服务部署:  节点名="+node_name);
            return true;
        }*/
       return true;
    }




    private String getLocByFree(String ip){//简单的免费接口
        RestTemplate restTmpl = new RestTemplate();
        String url = "http://freeapi.ipip.net/"+ip;
        String str = restTmpl.getForObject(url, String.class);
        List<String> list = JSON.parseArray(str, String.class);
        System.out.println("城市:"+list.get(2));
        return list.get(2);
    }

    private LocBody getLocByIpApi(String ip){//ipApi接口
        RestTemplate restTmpl = new RestTemplate();
        //String url = "http://freeapi.ipip.net/123.161.151.72";
        String url = "http://ip-api.com/json/"+ip+"?lang=zh-CN";
        ipApi str = restTmpl.getForObject(url, ipApi.class);
        System.out.println(str);
        LocBody loc = new LocBody(str.regionName, str.city, str.lon, str.lat);
        System.out.println(loc);
        return loc;
    }

    private LocBody getLocByGD(String ip){
        String key = "76388c9dc654c2c4df579c51bcf1984e";
        RestTemplate restTmpl = new RestTemplate();
        //https://restapi.amap.com/v3/ip?ip=123.161.150.106&key=76388c9dc654c2c4df579c51bcf1984e
        //String url = "http://freeapi.ipip.net/123.161.151.72";
        String url = "http://restapi.amap.com/v3/ip?ip="+ ip +"&key=" + key;
        AmapBody str = restTmpl.getForObject(url, AmapBody.class);
        System.out.println(str.rectangle);
        String rect1 = str.rectangle;
        String[] rect2 = rect1.split(";");
        //String[]
        //LocBody loc = new LocBody(str.province, str.city, str.rectangle[0][0], str.rectangle[0][1]);
        //System.out.println(loc);
        //return loc;
        return null;
    }
    public void getImage(String nodeName){
        String ip = nodeMapper.getNodeByNodeName(nodeName).getIp();
        System.out.println(ip);
        try {
            String exe = "python";
            String command = "C:\\Users\\guoxidong\\Desktop\\docketTest\\getimage.py";
            String[] cmdArr = new String[] { exe, command };
            Process process = Runtime.getRuntime().exec(cmdArr);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while( ( line = in.readLine() ) != null ) {
                System.out.println(line);
            }
            in.close();
            int result = process.waitFor();
            System.out.println("执行结果:" + result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void createImage(String nodeName){
        serviceMapper.createImage(nodeName,null,null,"registry.cn-hangzhou.aliyuncs.com/edge_node/image_system:eureka",false);
    }



    public void pytest(String ip){
        try {
            String exe = "python";
            String command = "C:\\Users\\guoxidong\\Desktop\\docketTest\\getimage.py";
            String[] cmdArr = new String[] { exe, command };
            Process process = Runtime.getRuntime().exec(cmdArr);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while( ( line = in.readLine() ) != null ) {
                System.out.println(line);
            }
            in.close();
            int result = process.waitFor();
            System.out.println("执行结果:" + result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
