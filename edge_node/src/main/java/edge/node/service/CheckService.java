package edge.node.service;

import com.alibaba.fastjson.JSON;
import edge.node.mapper.NodeMapper;
import edge.node.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CheckService {
    private final NodeMapper nodeMapper;

    public CheckService(NodeMapper nodeMapper){
        this.nodeMapper = nodeMapper;
    }

    public List<CheckDevice> check_device() {
        RestTemplate restTmpl = new RestTemplate();
        List<Integer> list_id = nodeMapper.get_node_id();
        List<CheckDevice> ans = new ArrayList<>();
        for (int n = 0; n < list_id.size(); n++) {
            String url = "http://localhost:"+(10000+(list_id.get(n).intValue()-1)*8+1)+"/device-service/devices/get_all";
            System.out.println(url);
            //List<DeviceAndTask> list = restTmpl.getForObject(url,List.class);
            String list = restTmpl.getForObject(url, String.class);
            List<DeviceAndTask> getdevice = JSON.parseArray(list, DeviceAndTask.class);

            for (int i = 0; i < getdevice.size(); i++) {
                CheckDevice checkDevice = new CheckDevice();
                String node_id = list_id.get(n).toString();
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

                checkDevice.setNode_id(node_id);
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
        return ans;
    }

    public List<CheckBracelet> check_bracelet(){
        RestTemplate restTmpl = new RestTemplate();
        List<Integer> list_id = nodeMapper.get_node_id();
        List<CheckBracelet> ans = new ArrayList<>();
        for (int n = 0; n < list_id.size(); n++) {
            String url = "http://localhost:" + (10000 + (list_id.get(n).intValue() - 1) * 8 + 1) + "/bracelet-service/bracelets/get_all_bracelet";
            System.out.println(url);
            String list = restTmpl.getForObject(url, String.class);
            List<Bracelet> getbracelet = JSON.parseArray(list, Bracelet.class);

            for (int i = 0; i < getbracelet.size(); i++) {
                CheckBracelet checkBracelet = new CheckBracelet();
                String node_id = "111";
                String bracelet_no = getbracelet.get(i).getBraceletNo();
                String device_no = getbracelet.get(i).getDeviceNo();
                boolean bracelet_status = getbracelet.get(i).getDeviceStatus();
                String prisoner_id = getbracelet.get(i).getPrisonerId();
                String cpu = "qualcomm snapdragon 865";
                String memory = "2GB";

                checkBracelet.setNode_id(node_id);
                checkBracelet.setBracelet_no(bracelet_no);
                checkBracelet.setDevice_no(device_no);
                checkBracelet.setBracelet_status(bracelet_status);
                checkBracelet.setPrisoner_id(prisoner_id);
                checkBracelet.setCpu(cpu);
                checkBracelet.setMemory(memory);

                ans.add(checkBracelet);
            }
        }
        System.out.println(ans);
        return ans;
    }
    public List<CheckVervel> check_vervel(){
        RestTemplate restTmpl = new RestTemplate();
        List<Integer> list_id = nodeMapper.get_node_id();
        List<CheckVervel> ans = new ArrayList<>();
        for (int n = 0; n < list_id.size(); n++) {
            String url = "http://localhost:" + (10000 + (list_id.get(n).intValue() - 1) * 8 + 1) + "/bracelet-service/bracelets/get_all_vervel";
            System.out.println(url);
            String list = restTmpl.getForObject(url, String.class);
            List<Vervel> getvervel = JSON.parseArray(list, Vervel.class);

            for (int i = 0; i < getvervel.size(); i++) {
                CheckVervel checkVerel = new CheckVervel();
                String node_id = "111";
                String vervel_no = getvervel.get(i).getVervelNo();
                String device_no = getvervel.get(i).getDeviceNo();
                boolean vervel_status = getvervel.get(i).getDeviceStatus();
                String prisoner_id = getvervel.get(i).getPrisonerId();
                String cpu = "qualcomm snapdragon 865";
                String memory = "2GB";

                checkVerel.setNode_id(node_id);
                checkVerel.setVervel_no(vervel_no);
                checkVerel.setDevice_no(device_no);
                checkVerel.setVervel_status(vervel_status);
                checkVerel.setPrisoner_id(prisoner_id);
                checkVerel.setCpu(cpu);
                checkVerel.setMemory(memory);

                ans.add(checkVerel);
            }
        }
        System.out.println(ans);
        return ans;
    }
}
