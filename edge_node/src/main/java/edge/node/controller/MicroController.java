package edge.node.controller;

import edge.node.service.MicroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/service")
@EnableAutoConfiguration
@Api(tags = "Service")
public class MicroController {
    private final MicroService microService;

    public MicroController(MicroService microService){
        this.microService = microService;
    }

    @RequestMapping(path = "/imagetest", method = RequestMethod.POST)
    @CrossOrigin
    public void getImage(/*String nodeName*/){
        microService.getImage("2414151");
    }

    public void pullImage(String nodeName){

    }

    public void deleteImage(String nodeName){

    }

    public void deployService(String nodeName, String serviceName){

    }

    public void offService(String nodename, String serviceName){

    }
}
