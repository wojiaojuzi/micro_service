package edge.node.schedule;

import edge.node.mapper.ContainerMapper;
import edge.node.mapper.ImageMapper;
import edge.node.mapper.NodeMapper;
import edge.node.model.Container;
import edge.node.model.Node;
import edge.node.model.ServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
@EnableScheduling
public class ContainerSchedule {
    private final NodeMapper nodeMapper;
    private final ContainerMapper containerMapper;
    private final ServicePort servicePort;

    @Autowired
    public ContainerSchedule(NodeMapper nodeMapper, ContainerMapper containerMapper, ServicePort servicePort) {
        this.nodeMapper = nodeMapper;
        this.containerMapper = containerMapper;
        this.servicePort = servicePort;
    }

    @Scheduled(fixedRate = 300)
    public void checkContainerAndService(){
        List<Node> nodeList = nodeMapper.get_all();
        for(int i=0 ; i<nodeList.size() ; i++){
            Node node = nodeList.get(i);
            String nodeName = node.getNodeName();
            String ip = node.getIp();
            List<Container> container_list = containerMapper.getContainerByNodeName(nodeName);
            for(int j=0; j<container_list.size(); j++) {
                Container container = container_list.get(j);
                if(container.getIsCreated() == true) {
                    try {
                        //System.out.println(container.getServiceName()+":"+servicePort.portmap.get(container.getServiceName()));
                        String exe = "python";
                        String command = "./docker-py/getContainer.py";
                        String[] cmdArr = new String[]{exe, command,container.getContainerName(), ip};
                        Process process = Runtime.getRuntime().exec(cmdArr);
                        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        String line;
                        line = in.readLine();
                        System.out.println(line);
                        if (!line.equals("error")) {
                        /*line = in.readLine();
                        System.out.println(line);
                        String recv[] = line.split(" ");*/
                            containerMapper.updateContainerStatusByNodeNameAndServiceName(nodeName, container.getServiceName(), line);
                            in.close();
                            if(line.equals("running")){
                                exe = "python";
                                command = "./docker-py/checkService.py";
                                cmdArr = new String[]{exe, command, ip,servicePort.portmap.get(container.getServiceName())};
                                process = Runtime.getRuntime().exec(cmdArr);
                                in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                                line = in.readLine();
                                System.out.println(container.getServiceName()+":"+line);
                                containerMapper.updateServiceStatusByNodeNameAndServiceName(nodeName,container.getServiceName(),line);
                            }
                        } else {
                            in.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
