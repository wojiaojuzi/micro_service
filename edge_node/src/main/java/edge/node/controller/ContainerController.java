package edge.node.controller;

import edge.node.model.Response.HttpResponseContent;
import edge.node.model.Response.ResponseEnum;
import edge.node.service.ContainerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

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
    public HttpResponseContent deleteImage(@Param("nodeName") String nodeName, @Param("serviceName")String serviceName){
        HttpResponseContent response = new HttpResponseContent();
        if(containerService.deleteImage(nodeName, serviceName)){
            response.setCode(ResponseEnum.SUCCESS.getCode());
            response.setMessage(ResponseEnum.SUCCESS.getMessage());
        }
        else{
            response.setCode(ResponseEnum.ERROR.getCode());
            response.setMessage(ResponseEnum.ERROR.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "部署服务")
    @RequestMapping(path = "/deployService", method = RequestMethod.POST)
    @CrossOrigin
    public HttpResponseContent deployService(@Param("nodeName") String nodeName, @Param("serviceName")String serviceName, @RequestHeader HttpHeaders headers){
        System.out.println(StringUtils.strip(headers.get("Authentication").toString(),"[]"));//token
        HttpResponseContent response = new HttpResponseContent();
        if(containerService.deployService(nodeName,serviceName)){
            response.setCode(ResponseEnum.SUCCESS.getCode());
            response.setMessage(ResponseEnum.SUCCESS.getMessage());
        }
        else{
            response.setCode(ResponseEnum.ERROR.getCode());
            response.setMessage(ResponseEnum.ERROR.getMessage());
        }
        return response;

    }

    @ApiOperation(value = "停止服务")
    @RequestMapping(path = "/offService", method = RequestMethod.POST)
    @CrossOrigin
    public HttpResponseContent offService(String nodeName, String serviceName){
        HttpResponseContent response = new HttpResponseContent();
        if(containerService.offService(nodeName,serviceName)){
            response.setCode(ResponseEnum.SUCCESS.getCode());
            response.setMessage(ResponseEnum.SUCCESS.getMessage());
        }
        else{
            response.setCode(ResponseEnum.ERROR.getCode());
            response.setMessage(ResponseEnum.ERROR.getMessage());
        }
        return response;
    }
}
