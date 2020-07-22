package edge.node.service;

import edge.node.model.serviceport;
import edge.node.mapper.NodeMapper;
import edge.node.mapper.ServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class MicroService {
    private final ServiceMapper serviceMapper;
    private final NodeMapper nodeMapper;
    @Autowired
    private final serviceport servicePort;

    @Autowired
    public MicroService(ServiceMapper serviceMapper, NodeMapper nodeMapper, serviceport servicePort){
        this.serviceMapper = serviceMapper;
        this.nodeMapper = nodeMapper;
        this.servicePort = servicePort;
    }

    public void getImage(String nodeName){
        String ip = nodeMapper.getNodeByNodeName(nodeName).getIp();
        System.out.println(ip);
        try {
            String exe = "python";
            String command = "C:\\Users\\guoxidong\\Desktop\\docketTest\\getImage.py";
            String[] cmdArr = new String[] { exe, command };
            Process process = Runtime.getRuntime().exec(cmdArr);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while( ( line = in.readLine() ) != null ) {
                System.out.println(line);
                //去掉空格   获取各个字段的值
                String[] sp = line.split(" ");
                for(String str : sp)
                    System.out.println(str);
                serviceMapper.updataImageIdByNodeNameAndImageRepositoryAndImageTag(nodeName,sp[2],sp[3],sp[0]);
                serviceMapper.updataImageShortIdByNodeNameAndImageRepositoryAndImageTag(nodeName,sp[2],sp[3],sp[1]);
                serviceMapper.updataImageStatusByNodeNameAndImageRepositoryAndImageTag(nodeName,sp[2],sp[3],true);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pullImage(String nodeName){
        String ip = nodeMapper.getNodeByNodeName(nodeName).getIp();
        System.out.println(ip);
        try {
            String exe = "python";
            String command = "C:\\Users\\guoxidong\\Desktop\\docketTest\\getImage.py";
            String[] cmdArr = new String[] { exe, command };
            Process process = Runtime.getRuntime().exec(cmdArr);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while( ( line = in.readLine() ) != null ) {
                System.out.println(line);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteImage(/*String nodeName,*/ String imageRepository, String imageTag){
        try {
            String exe = "python";
            String command = "C:\\Users\\guoxidong\\Desktop\\docketTest\\deleteImage.py";
            String[] cmdArr = new String[] { exe, command, imageRepository, imageTag};
            Process process = Runtime.getRuntime().exec(cmdArr);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while( ( line = in.readLine() ) != null ) {
                System.out.println(line);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean createNetwork(String nodeName){
        String ip = nodeMapper.getNodeByNodeName(nodeName).getIp();
        try {
            String exe = "python";
            String command = "C:\\Users\\guoxidong\\Desktop\\docketTest\\createNetwork.py";
            String[] cmdArr = new String[] { exe, command,ip};
            Process process = Runtime.getRuntime().exec(cmdArr);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while( ( line = in.readLine() ) != null ) {
                System.out.println(line);
                if(line.equals("create success")){
                    System.out.println("创建网络成功");
                    return true;
                }
                else{
                    System.out.println("创建网络失败");
                    return false;
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deployService(String nodeName, String serviceName){
        String ip = nodeMapper.getNodeByNodeName(nodeName).getIp();
        String port = servicePort.portmap.get(serviceName);
        String repository = serviceMapper.getRepositoryByNodeNameAndServiceName(nodeName,serviceName);
        String tag = serviceMapper.getTagByNodeNameAndServiceName(nodeName,serviceName);
        if(createNetwork(nodeName)==true){
            try {
                String exe = "python";
                String command = "C:\\Users\\guoxidong\\Desktop\\docketTest\\runContainer.py";
                String[] cmdArr = new String[] { exe, command,repository,tag,ip,port,serviceName};
                Process process = Runtime.getRuntime().exec(cmdArr);
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while( ( line = in.readLine() ) != null ) {
                    System.out.println(line);
                    if(line.equals("create success")){
                        System.out.println(serviceName+"容器启动成功");
                        return true;
                    }
                    else{
                        System.out.println(serviceName+"容器启动失败");
                        return false;
                    }
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println(serviceName+"容器启动失败");
            return false;
        }
        return false;
    }

    public boolean offService(String nodeName, String serviceName){
        String ip = nodeMapper.getNodeByNodeName(nodeName).getIp();
            try {
                String exe = "python";
                String command = "C:\\Users\\guoxidong\\Desktop\\docketTest\\offContainer.py";
                String[] cmdArr = new String[] { exe, command,serviceName,ip};
                Process process = Runtime.getRuntime().exec(cmdArr);
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while( ( line = in.readLine() ) != null ) {
                    System.out.println(line);
                    if(line.equals("close success")){
                        System.out.println(serviceName+"容器关闭成功");
                        return true;
                    }
                    else{
                        System.out.println(serviceName+"容器关闭失败");
                        return false;
                    }
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return false;
    }
}
