package edge.node.service;


import com.alibaba.fastjson.JSON;
import edge.node.mapper.ContainerMapper;
import edge.node.mapper.NodeMapper;
import edge.node.mapper.ImageMapper;
import edge.node.model.*;
import edge.node.model.api.LogFeign;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.text.SimpleDateFormat;
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
    private final ImageMapper imageMapper;
    private final LogFeign logFeign;
    private final ContainerMapper containerMapper;
    private static final Logger logger = LogManager.getLogger(NodeService.class);

    public NodeService(NodeMapper nodeMapper, LogFeign logFeign, ImageMapper imageMapper,ContainerMapper containerMapper){
        this.nodeMapper = nodeMapper;
        this.logFeign = logFeign;
        this.imageMapper = imageMapper;
        this.containerMapper = containerMapper;
    }

    //节点注册逻辑要求改
    public Node nodeRegister(Node node,String account){
        Date createTime = new Date();
        SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
        node.setNodeCreateAt(mysqlSdf.format(createTime));
        System.out.println(node);
        //if(isPing(node.getIp())) {
            nodeMapper.create_node(node.getNodeName(), node.getArea(), node.getLocation(), node.getLon(),
                    node.getLat(), node.getIp(), node.getCpu(),
                    node.getCpuRate(), node.getMemory(),
                    node.getMemRate(), node.getFrequence(), false,node.getNodeCreateAt());
            imageMapper.createImage(node.getNodeName(),null,null,"registry.cn-hangzhou.aliyuncs.com/edge_node/eureka","latest","未下载","eureka");
            imageMapper.createImage(node.getNodeName(),null,null,"registry.cn-hangzhou.aliyuncs.com/edge_node/zuul","latest","未下载","zuul");
            containerMapper.createContainer(node.getNodeName(),"eureka","","eureka",null,null,"uncreated",false);
            containerMapper.createContainer(node.getNodeName(),"zuul","","zuul",null,null,"uncreated",false);
            Node test = nodeMapper.getNodeByNodeName(node.getNodeName());
            if (test == null)
                return null;
            else {
                logFeign.addLog(account, "注册节点:  节点名=" + node.getNodeName() + " , 位置=" + node.getLocation());
                return test;
            }
        //}
        //System.out.println("ip不可用");
        //return null;
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
    public boolean nodeDelete(String nodeName,String account){
        Node node = nodeMapper.getNodeByNodeName(nodeName);
        List<Image> imageList = imageMapper.getAllImage(nodeName);
        for(Image image : imageList){
            if(image.getImageStatus().equals("已下载")){
                try {
                    String exe = "python";
                    String command = "./docker-py/deleteImage.py";
                    String[] cmdArr = new String[] { exe, command, image.getImageRepository(),image.getImageTag(),node.getIp()};
                    Process process = Runtime.getRuntime().exec(cmdArr);
                    BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    line = in.readLine();
                    System.out.println(line);
                    if(line.equals("delete success")){
                        imageMapper.updataImageStatusByNodeNameAndServiceName(nodeName,image.getServiceName(),"未下载");
                        in.close();
                    }
                    else{
                        in.close();
                        return false;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        nodeMapper.deleteNodeByNodeName(nodeName);
        //imageMapper.deleteByNodeName(nodeName);
        //containerMapper.deleteContainerByNodeName(nodeName);
        logFeign.addLog(account,"删除节点:  节点名="+nodeName);
        return true;
    }

    public Node nodeGet(String node_name){ return nodeMapper.getNodeByNodeName(node_name);}

    public List<Node> get_all(){ return nodeMapper.get_all(); }



/*    private String getLocByFree(String ip){//简单的免费接口
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
        IpApi str = restTmpl.getForObject(url, IpApi.class);
        System.out.println(str);
        LocBody loc = new LocBody(str.regionName, str.city, str.lon, str.lat);
        System.out.println(loc);
        return loc;
    }*/

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

}
