package edge.node.server;

import edge.node.mapper.ContainerMapper;
import edge.node.mapper.DeviceTestMapper;
import edge.node.mapper.ImageMapper;
import edge.node.mapper.NodeMapper;
import edge.node.model.Container;
import edge.node.model.DeviceTest;
import edge.node.model.Image;
import edge.node.model.Node;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@EnableScheduling
public class PushSchedule {
    private final NodeMapper nodeMapper;
    private final ImageMapper imageMapper;
    private final ContainerMapper containerMapper;
    private final DeviceTestMapper deviceTestMapper;

    @Autowired
    public PushSchedule(NodeMapper nodeMapper,ImageMapper imageMapper,ContainerMapper containerMapper,DeviceTestMapper deviceTestMapper){
        this.nodeMapper = nodeMapper;
        this.imageMapper = imageMapper;
        this.containerMapper = containerMapper;
        this.deviceTestMapper = deviceTestMapper;
    }

    /**
     * 推送消息到前台
     */
    @Scheduled(cron = "*/1 * * * * * ")
    public void mapSocketMessage(){
        Map<String, Object> maps = new HashMap<>();
        maps.put("type", "sendMessage");
        maps.put("data","11111");
        MapServer.sendInfo(maps);
    }

    @Scheduled(cron = "*/1 * * * * * ")
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

    @Scheduled(cron = "*/1 * * * * * ")
    public void ServiceSocketMessage() {
        Map<String, Object> maps = new HashMap<>();
        if (NodeServer.getOnlineCount() > 0)
            maps.put("type", "containerData");
        List<Node> nodeList = nodeMapper.get_all();
        List<List<Container>> pushContainerData = new ArrayList<List<Container>>();
        for (int i = 0; i < nodeList.size(); i++) {
            String nodeName = nodeList.get(i).getNodeName();
            List<Container> ContainerData = containerMapper.getContainerByNodeName(nodeName);
            pushContainerData.add(ContainerData);
        }
        maps.put("data", pushContainerData);
        NodeServer.sendInfo(maps);
    }

    @Scheduled(cron = "*/1 * * * * * ")
    public void NodeSocketMessage() {
        Map<String, Object> maps = new HashMap<>();
        if (MapServer.getOnlineCount() > 0){
            maps.put("type", "nodeData");
            List<Node> nodeList = nodeMapper.get_all();
            maps.put("data", nodeList);
            MapServer.sendInfo(maps);
        }
    }
    @Scheduled(cron = "*/1 * * * * * ")
    public void DeviceSocketMessage() {
        Map<String, Object> maps = new HashMap<>();
        if (MapServer.getOnlineCount() > 0){
            maps.put("type", "deviceData");
            List<DeviceTest> deviceTestList= deviceTestMapper.getAllDevice();
            maps.put("data", deviceTestList);
            MapServer.sendInfo(maps);
        }
    }

}
