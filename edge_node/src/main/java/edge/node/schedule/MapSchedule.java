package edge.node.schedule;

import edge.node.mapper.DeviceTestMapper;
import edge.node.mapper.NodeMapper;
import edge.node.model.DeviceTest;
import edge.node.model.Node;
import edge.node.server.MapServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
public class MapSchedule {
    private final NodeMapper nodeMapper;
    private final DeviceTestMapper deviceTestMapper;

    @Autowired
    public MapSchedule(NodeMapper nodeMapper, DeviceTestMapper deviceTestMapper) {
        this.nodeMapper = nodeMapper;
        this.deviceTestMapper = deviceTestMapper;
    }

    /*-------------Map------------------*/
    //@Scheduled(cron = "*/1 * * * * * ")
    @Scheduled(fixedRate = 100)
    public void NodeSocketMessage() {
        Map<String, Object> maps = new HashMap<>();
        if (MapServer.getOnlineCount() > 0){
            maps.put("type", "nodeData");
            List<Node> nodeList = nodeMapper.get_all();
            //System.out.println(nodeList);
            maps.put("data", nodeList);
            MapServer.sendInfo(maps);
        }
    }
    //@Scheduled(cron = "*/1 * * * * * ")
    @Scheduled(fixedRate = 100)
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
