package edge.node.service;

import edge.node.mapper.ContainerMapper;
import edge.node.model.Image;
import edge.node.model.serviceport;
import edge.node.mapper.NodeMapper;
import edge.node.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ContainerService {
    private final ImageMapper imageMapper;
    private final NodeMapper nodeMapper;
    private final ContainerMapper containerMapper;
    @Autowired
    private final serviceport servicePort;

    @Autowired
    public ContainerService(ImageMapper imageMapper, NodeMapper nodeMapper,
                            ContainerMapper containerMapper, serviceport servicePort){
        this.imageMapper = imageMapper;
        this.nodeMapper = nodeMapper;
        this.containerMapper = containerMapper;
        this.servicePort = servicePort;
    }

    public void getImage(String nodeName){
        Image im = imageMapper.getByNodeNameAndServiceName("11111","eureka");
    }

    public boolean pullImage(String nodeName, String serviceName){
        String imageStatus = imageMapper.getImageStatusByNodeNameAndServiceName(nodeName,serviceName);
        if(imageStatus.equals("未下载")) {
            try {
                String ip = nodeMapper.getNodeByNodeName(nodeName).getIp();
                String repository = imageMapper.getRepositoryByNodeNameAndServiceName(nodeName,serviceName);
                String tag = imageMapper.getTagByNodeNameAndServiceName(nodeName,serviceName);
                String exe = "python";
                String command = "./docker-py/pullImage.py";
                String[] cmdArr = new String[]{exe, command, repository, tag, ip};
                Process process = Runtime.getRuntime().exec(cmdArr);
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                    if(line.equals("pull success")){
                        line = in.readLine();
                        String recv[] = line.split(" ");
                        System.out.println(recv[0]+" "+recv[1]);
                        imageMapper.updataImageIdByNodeNameAndServiceName(nodeName,serviceName,recv[0]);
                        imageMapper.updataImageShortIdByNodeNameAndServiceName(nodeName,serviceName,recv[1]);
                        imageMapper.updataImageStatusByNodeNameAndServiceName(nodeName,serviceName,"已下载");
                    }
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            return false;
        }
        return false;
    }

    public boolean deleteImage(String nodeName, String serviceName){
        String ip = nodeMapper.getNodeByNodeName(nodeName).getIp();
        String repository = imageMapper.getRepositoryByNodeNameAndServiceName(nodeName,serviceName);
        String tag = imageMapper.getTagByNodeNameAndServiceName(nodeName,serviceName);
        String imageStatus = imageMapper.getImageStatusByNodeNameAndServiceName(nodeName,serviceName);
        if(imageStatus.equals("已下载")) {
            try {
                String exe = "python";
                String command = "./docker-py/deleteImage.py";
                String[] cmdArr = new String[]{exe, command, repository, tag, ip};
                Process process = Runtime.getRuntime().exec(cmdArr);
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;

                line = in.readLine();
                System.out.println(line);
                if(line.equals("delete success")){
                    /*line = in.readLine();
                    System.out.println(line);
                    String recv[] = line.split(" ");*/
                    imageMapper.updataImageStatusByNodeNameAndServiceName(nodeName,serviceName,"未下载");

                    in.close();
                    return true;
                }
                else{
                    in.close();
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean createNetwork(String nodeName){
        String ip = nodeMapper.getNodeByNodeName(nodeName).getIp();
        try {
            String exe = "python";
            String command = "./docker-py/createNetwork.py";
            String[] cmdArr = new String[] { exe, command,ip};
            Process process = Runtime.getRuntime().exec(cmdArr);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            if( ( line = in.readLine() ) != null ) {
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
        String repository = imageMapper.getRepositoryByNodeNameAndServiceName(nodeName,serviceName);
        String tag = imageMapper.getTagByNodeNameAndServiceName(nodeName,serviceName);
        String imageStatus = imageMapper.getImageStatusByNodeNameAndServiceName(nodeName, serviceName);
        if(createNetwork(nodeName)==true && imageStatus.equals("已下载")){
            try {
                //File dir = new File("D:\\micro_service\\docker-py");
                String exe = "python";
                String command = "./docker-py/runContainer.py";
                String[] cmdArr = new String[] { exe, command,repository,tag,ip,port,serviceName};
                Process process = Runtime.getRuntime().exec(cmdArr);
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                line = in.readLine() ;
                        System.out.println(line);
                        if(line.equals("create success")){
                            line = in.readLine();
                            String[] args = line.split(" ");
                            //System.out.println(args);
                            //containerMapper.createContainer(nodeName, serviceName,"null",args[0],args[1],args[2],args[3]);
                            containerMapper.updateserviceStatusByNodeNameAndServiceName(nodeName, serviceName,"null");
                            containerMapper.updatecontainerIdByNodeNameAndServiceName(nodeName, serviceName,args[0]);
                            containerMapper.updatecontainerIdByNodeNameAndServiceName(nodeName, serviceName,args[1]);
                            containerMapper.updatecontainerShortIdByNodeNameAndServiceName(nodeName, serviceName,args[2]);
                            containerMapper.updateContainerStatusByNodeNameAndServiceName(nodeName, serviceName,args[3]);
                            System.out.println(serviceName+"容器启动成功");
                            return true;
                        }
                    else{
                            System.out.println(serviceName+"容器启动失败");
                            return false;
                        }
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
                String command = "./docker-py/offContainer.py";
                String[] cmdArr = new String[] { exe, command,serviceName,ip};
                Process process = Runtime.getRuntime().exec(cmdArr);
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                System.out.println("line之前");
                    line = in.readLine();
                    System.out.println(line);
                System.out.println("line之后");
                    if(line.equals("close success")){
                        System.out.println(serviceName+"容器关闭成功");
                        containerMapper.updateContainerStatusByNodeNameAndServiceName(nodeName, serviceName,"uncreated");
                        containerMapper.updateserviceStatusByNodeNameAndServiceName(nodeName, serviceName,"null");
                        in.close();
                        return true;
                    }
                    else{
                        System.out.println(serviceName+"容器关闭失败");
                        in.close();
                        return false;
                    }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return false;
    }
}
