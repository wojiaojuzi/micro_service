package edge.node.controller;

import edge.node.model.Response.HttpResponseContent;
import edge.node.model.Response.ResponseEnum;
import edge.node.service.ContainerService;
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
public class ContainerController {
    private final ContainerService containerService;

    public ContainerController(ContainerService containerService){
        this.containerService = containerService;
    }

    @ApiOperation(value = "获取镜像")
    @RequestMapping(path = "/getImage", method = RequestMethod.POST)
    @CrossOrigin
    public void getImage(/*String nodeName*/){
        containerService.getImage("2414151");
    }

    @ApiOperation(value = "下载镜像")
    @RequestMapping(path = "/pullImage", method = RequestMethod.POST)
    @CrossOrigin
    public HttpResponseContent pullImage(@Param("nodeName") String nodeName, @Param("serviceName")String serviceName){
        HttpResponseContent response = new HttpResponseContent();
        if(containerService.pullImage(nodeName,serviceName)){
            response.setCode(ResponseEnum.SUCCESS.getCode());
            response.setMessage(ResponseEnum.SUCCESS.getMessage());
        }
        else{
            response.setCode(ResponseEnum.ERROR.getCode());
            response.setMessage(ResponseEnum.ERROR.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "删除镜像")
    @RequestMapping(path = "/deleteImage", method = RequestMethod.POST)
    @CrossOrigin
    public void deleteImage(@Param("nodeName") String nodeName, @Param("serviceName")String serviceName){
        System.out.println("删除镜像进来");
        containerService.deleteImage(nodeName, serviceName);
    }

    @ApiOperation(value = "部署服务")
    @RequestMapping(path = "/deployService", method = RequestMethod.POST)
    @CrossOrigin
    public void deployService(@Param("nodeName") String nodeName, @Param("serviceName")String serviceName){
        containerService.deployService(nodeName,serviceName);
    }

    @ApiOperation(value = "停止服务")
    @RequestMapping(path = "/offService", method = RequestMethod.POST)
    @CrossOrigin
    public void offService(String nodeName, String serviceName){
        containerService.offService(nodeName,serviceName);
    }
}
