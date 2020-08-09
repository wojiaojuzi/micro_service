package edge.node.schedule;

import edge.node.mapper.ContainerMapper;
import edge.node.mapper.ImageMapper;
import edge.node.mapper.NodeMapper;
import edge.node.model.Container;
import edge.node.model.Image;
import edge.node.model.Node;
import edge.node.model.ServicePort;
import edge.node.server.NodeServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
public class NodeSchedule {
    private final NodeMapper nodeMapper;
    private final ImageMapper imageMapper;
    private final ContainerMapper containerMapper;
    private final ServicePort servicePort;

    @Autowired
    public NodeSchedule(NodeMapper nodeMapper, ImageMapper imageMapper, ContainerMapper containerMapper, ServicePort servicePort) {
        this.nodeMapper = nodeMapper;
        this.imageMapper = imageMapper;
        this.containerMapper = containerMapper;
        this.servicePort = servicePort;
    }

    //@Scheduled(cron = "*/1 * * * * * ")
    @Scheduled(fixedRate = 100)
    public void ImageSocketMessage() {
        Map<String, Object> maps = new HashMap<>();
        if (NodeServer.getOnlineCount() > 0)
            maps.put("type", "imageData");
        List<Node> nodeList = nodeMapper.get_all();
        List<List<Image>> pushImageData = new ArrayList<List<Image>>();
        for (int i = 0; i < nodeList.size(); i++) {
            String nodeName = nodeList.get(i).getNodeName();
            List<Image> imageData = imageMapper.getAllImage(nodeName);
            pushImageData.add(imageData);
        }
        maps.put("data", pushImageData);
        NodeServer.sendInfo(maps);
    }

    //@Scheduled(cron = "*/1 * * * * * ")
    @Scheduled(fixedRate = 100)
    public void ServiceSocketMessage() {
        List<Node> nodeList = nodeMapper.get_all();


        Map<String, Object> maps = new HashMap<>();
        if (NodeServer.getOnlineCount() > 0) {
            maps.put("type", "containerData");
            //List<Node> nodeList = nodeMapper.get_all();
            List<List<Container>> pushContainerData = new ArrayList<List<Container>>();
            for (int i = 0; i < nodeList.size(); i++) {
                String nodeName = nodeList.get(i).getNodeName();
                List<Container> ContainerData = containerMapper.getContainerByNodeName(nodeName);
                pushContainerData.add(ContainerData);
            }
            maps.put("data", pushContainerData);
            NodeServer.sendInfo(maps);
        }
    }
}
