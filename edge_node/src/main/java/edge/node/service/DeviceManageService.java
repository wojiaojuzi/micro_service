package edge.node.service;


import edge.node.mapper.NodeMapper;
import org.springframework.stereotype.Service;

@Service
public class DeviceManageService {
    private final NodeMapper nodeMapper;

    public DeviceManageService(NodeMapper nodeMapper){
        this.nodeMapper = nodeMapper;
    }

    

}
