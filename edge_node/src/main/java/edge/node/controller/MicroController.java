package edge.node.controller;

import edge.node.service.MicroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping(path = "/service")
@EnableAutoConfiguration
@Api(tags = "Service")
public class MicroController {
    private final MicroService microService;

    public MicroController(MicroService microService){
        this.microService = microService;
    }

    @RequestMapping(path = "/getImage", method = RequestMethod.POST)
    @CrossOrigin
    public void getImage(/*String nodeName*/){
        microService.getImage("2414151");
    }

    @RequestMapping(path = "/pullImage", method = RequestMethod.POST)
    @CrossOrigin
    public void pullImage(/*String nodeName*/){
        try {
            String exe = "python";
            String command = "C:\\Users\\guoxidong\\Desktop\\docketTest\\pullImage.py";
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

    @RequestMapping(path = "/deleteImage", method = RequestMethod.POST)
    @CrossOrigin
    public void deleteImage(/*String nodeName,*/ String imageRepository, String imageTag){
        microService.deleteImage(imageRepository, imageTag);
    }
    @RequestMapping(path = "/deployService", method = RequestMethod.POST)
    @CrossOrigin
    public void deployService(@Param("nodeName") String nodeName, @Param("serviceName")String serviceName){
        microService.deployService(nodeName,serviceName);
    }

    @RequestMapping(path = "/offService", method = RequestMethod.POST)
    @CrossOrigin
    public void offService(String nodeName, String serviceName){
        microService.offService(nodeName,serviceName);
    }
}
