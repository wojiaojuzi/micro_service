package edge.node.controller;


import edge.node.service.DeviceManageService;
import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/device")
@EnableAutoConfiguration
@Api(tags = "Device", description = "设备管理相关操作")
public class DeviceManageController {
    private final DeviceManageService deviceManageService;

    public DeviceManageController(DeviceManageService deviceManageService){
        this.deviceManageService = deviceManageService;
    }
}
