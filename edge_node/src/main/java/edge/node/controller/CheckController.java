package edge.node.controller;

import edge.node.model.CheckBracelet;
import edge.node.model.CheckDevice;
import edge.node.model.CheckVervel;
import edge.node.service.CheckService;
import edge.node.service.NodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/check")
@EnableAutoConfiguration
@Api(tags = "Check", description = "边缘设备相关操作")
public class CheckController {
    private final NodeService nodeService;
    private final CheckService checkService;

    public CheckController(NodeService nodeService, CheckService checkService) {
        this.nodeService = nodeService;
        this.checkService = checkService;
    }

    @ApiOperation(value = "查看边缘设备信息")
    @RequestMapping(path = "/device")
    @CrossOrigin
    public List<CheckDevice> check_device(){
        return checkService.check_device();
    }

    @ApiOperation(value = "查看手环信息")
    @RequestMapping(path = "/bracelet")
    @CrossOrigin
    public List<CheckBracelet> check_bracelet(){
        return checkService.check_bracelet();
    }

    @ApiOperation(value = "查看手环信息")
    @RequestMapping(path = "/vervel")
    @CrossOrigin
    public List<CheckVervel> check_vervel(){
        return checkService.check_vervel();
    }
}
