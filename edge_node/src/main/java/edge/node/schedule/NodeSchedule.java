package edge.node.schedule;

import com.alibaba.fastjson.JSON;
import edge.node.mapper.NodeMapper;
import edge.node.model.*;
import org.omg.CORBA.portable.OutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//定时任务类 用于周期性地发起请求
@Component
//@EnableScheduling
public class NodeSchedule {
    @Autowired
    private NodeMapper nodeMapper;

   // @Scheduled(fixedRate = 3000)
    private void test() throws IOException {
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        //System.out.println("当前时间:"+df.format(new Date()));

        /*Process proc = null;
        String cmd = new String("docker -H 192.168.0.105:2375 images");
        proc = Runtime.getRuntime().exec(cmd);

        InputStream inputStream = proc.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            //去掉空格   获取各个字段的值
            String[] sp = line.split("\\s+ ");
            for(String str : sp)
               System.out.println(str);

        }*/
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("当前时间:"+df.format(new Date()));
        //由Ip获取地理位置
        //RestTemplate restTmpl = new RestTemplate();
        //String url = "http://freeapi.ipip.net/123.161.151.72";
        //String url = "http://ip-api.com/json/123.161.151.72?lang=zh-CN";
        //ipApi str = restTmpl.getForObject(url, ipApi.class);
        //System.out.println(str);

        //getLocByGD("123.161.151.72");
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
        //LocBody loc = new LocBody(str.province, str.city, str.rectangle[0][0], str.rectangle[0][1]);
        //System.out.println(loc);
        //return loc;
        return null;
    }

   // @Scheduled(fixedRate = 2000)
    private void getNode(){
        System.out.println(nodeMapper.get_all());
    }

    //@Scheduled(fixedRate = 10000)//定时获取并更新节点信息
    private void check_node() {
        RestTemplate restTmpl = new RestTemplate();
        List<String> node_ip = nodeMapper.get_node_ip();
        String port = "5555";
        for(String ip : node_ip){
            String url = "http://"+ip+":"+port+"/";
            System.out.println(url);

            String list = restTmpl.getForObject(url, String.class);
            List<Node> getdevice = JSON.parseArray(list, Node.class);
            for (int i = 0; i < getdevice.size(); i++) {//获取的节点信息存入数据库
                //CheckDevice checkDevice = new CheckDevice();
                //String node_id = list_id.get(n).toString();
                //String device_no = getdevice.get(i).getDevice().getDeviceNo();
                //String device_type = getdevice.get(i).getDevice().getDeviceType();
                //String user_id = getdevice.get(i).getDevice().getUid();
                //String user_name = getdevice.get(i).getDevice().getUser_name();
                //boolean device_status = getdevice.get(i).getDevice().getDeviceStatus();
                //String cpu;
                //if (device_type.equals("pad"))
                //    cpu = "Apple A12";
                //else
                //    cpu = "麒麟990";
                //String memory = "2GB";
                //String task_no = getdevice.get(i).getTask().getTaskNo();
                //String task_detail = getdevice.get(i).getTask().getDetail();
                //String prisoner_name = getdevice.get(i).getTask().getPrisonerName();
                //String car_no = getdevice.get(i).getTask().getCarNo();
            }
        }
    }
    //@Scheduled(fixedRate = 10000)//周期获取设备的信息
    private void check_device() {
        RestTemplate restTmpl = new RestTemplate();
        List<CheckDevice> ans = new ArrayList<>();
        List<String> node_ip = nodeMapper.get_node_ip();
        String port = "5555";
        for(String ip : node_ip){
            String url = "http://"+ip+":"+port+"/";

            System.out.println(url);
            //List<DeviceAndTask> list = restTmpl.getForObject(url,List.class);
            String list = restTmpl.getForObject(url, String.class);
            List<DeviceAndTask> getdevice = JSON.parseArray(list, DeviceAndTask.class);

            for (int i = 0; i < getdevice.size(); i++) {
                CheckDevice checkDevice = new CheckDevice();
                //String node_id = list_id.get(n).toString();
                String device_no = getdevice.get(i).getDevice().getDeviceNo();
                String device_type = getdevice.get(i).getDevice().getDeviceType();
                String user_id = getdevice.get(i).getDevice().getUid();
                String user_name = getdevice.get(i).getDevice().getUser_name();
                boolean device_status = getdevice.get(i).getDevice().getDeviceStatus();
                String cpu;
                if (device_type.equals("pad"))
                    cpu = "Apple A12";
                else
                    cpu = "麒麟990";
                String memory = "2GB";
                String task_no = getdevice.get(i).getTask().getTaskNo();
                String task_detail = getdevice.get(i).getTask().getDetail();
                String prisoner_name = getdevice.get(i).getTask().getPrisonerName();
                String car_no = getdevice.get(i).getTask().getCarNo();

               // checkDevice.setNode_id(node_id);
                checkDevice.setDevice_no(device_no);
                checkDevice.setDevice_type(device_type);
                checkDevice.setUser_id(user_id);
                checkDevice.setUser_name(user_name);
                checkDevice.setDevice_status(device_status);
                checkDevice.setCpu(cpu);
                checkDevice.setMemory(memory);
                checkDevice.setTask_no(task_no);
                checkDevice.setDetail(task_detail);
                checkDevice.setPrisoner_name(prisoner_name);
                checkDevice.setCar_no(car_no);

                ans.add(checkDevice);

            }
        }
        System.out.println(ans);
    }

}
