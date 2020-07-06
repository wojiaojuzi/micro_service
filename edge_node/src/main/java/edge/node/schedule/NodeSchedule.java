package edge.node.schedule;

import com.alibaba.fastjson.JSON;
import edge.node.mapper.NodeMapper;
import edge.node.model.CheckDevice;
import edge.node.model.DeviceAndTask;
import edge.node.model.Node;
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

    @Scheduled(fixedRate = 3000)
    private void test() throws IOException {
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        //System.out.println(System.getProperty("cpu.name"));
        //System.out.println("当前时间:"+df.format(new Date()));
        /*DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://192.168.0.105:2375").build();
        //
        Info info = (Info) dockerClient.listImagesCmd();
        System.out.println(info);*/
        Process proc = null;
        String cmd = new String("docker -H 192.168.0.105:2375 images");
        proc = Runtime.getRuntime().exec(cmd);

        InputStream inputStream = proc.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            //去掉空格  获取各个字段的值
            String[] sp = line.split("\\s+ ");
            for(String str : sp)
               System.out.println(str);

        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        //System.out.println(System.getProperty("cpu.name"));
        System.out.println("当前时间:"+df.format(new Date()));
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
