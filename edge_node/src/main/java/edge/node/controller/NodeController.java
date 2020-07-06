package edge.node.controller;


import edge.node.model.Node;
import edge.node.model.Response.HttpResponseContent;
import edge.node.model.Response.ResponseEnum;
import edge.node.model.return_location;
import edge.node.service.NodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/node")
@EnableAutoConfiguration
@Api(tags = "Node")
public class NodeController {
    private final NodeService nodeService;

    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @ApiOperation(value = "节点注册")
    @RequestMapping(path = "/register")
    @CrossOrigin
    public HttpResponseContent nodeRegister(@RequestParam("nodeName") String node_name,
                                            @RequestParam("location") String location,
                                            @RequestParam("account") String account,
                                            @RequestParam("cpu") String cpu,
                                            @RequestParam("memory") String memory) throws Exception {
        HttpResponseContent response = new HttpResponseContent();
        Node node = new Node(node_name, location, cpu, memory);
        Node test = nodeService.nodeRegister(node,account);
        if (test == null) {
            response.setCode(ResponseEnum.LOGIN_FAILED.getCode());
            response.setMessage(ResponseEnum.LOGIN_FAILED.getMessage());
        } else {
            response.setCode(ResponseEnum.SUCCESS.getCode());
            response.setMessage(ResponseEnum.SUCCESS.getMessage());
            response.setData(test);
        }
        return response;
    }

    @ApiOperation(value = "节点删除")
    @RequestMapping(path = "/delete")
    @CrossOrigin
    public HttpResponseContent nodeDelete(@RequestParam("nodeName") String node_name,@RequestParam("account") String account) throws Exception {
        HttpResponseContent response = new HttpResponseContent();
        nodeService.nodeDelete(node_name,account);

        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());

        return response;
    }

    @ApiOperation(value = "查看节点信息")
    @RequestMapping(path = "/get")
    @CrossOrigin
    public Node nodeGet(@RequestParam("nodeName") String node_name) throws Exception {
        return nodeService.nodeGet(node_name);
    }

    @ApiOperation(value = "查看所有节点")
    @RequestMapping(path = "/getAll")
    @CrossOrigin
    public List<Node> get_all() throws Exception {
        return nodeService.get_all();
    }

    @ApiOperation(value = "查看节点数量")
    @RequestMapping(path = "/get_node_num")
    @CrossOrigin
    public int get_node_num() throws Exception {
        return nodeService.get_all().size();
    }

    @ApiOperation(value = "查看正在运行节点数量")
    @RequestMapping(path = "/get_on_num")
    @CrossOrigin
    public int get_on_num() throws Exception {
        return nodeService.get_on_num();
    }

    @ApiOperation(value = "查看所有节点位置")
    @RequestMapping(path = "/getAllLocation")
    @CrossOrigin
    public List<return_location> get_all_location() throws Exception {
        return nodeService.get_all_location();
    }

    @ApiOperation(value = "查看启动节点位置")
    @RequestMapping(path = "/getOnLocation")
    @CrossOrigin
    public List<return_location> get_on_location() throws Exception {
        return nodeService.get_on_location();
    }

    @ApiOperation(value = "查看关闭节点位置")
    @RequestMapping(path = "/getOffLocation")
    @CrossOrigin
    public List<return_location> get_off_location() throws Exception {
        return nodeService.get_off_location();
    }

    @ApiOperation(value = "节点微服务部署")
    @RequestMapping(path = "/deploy")
    @CrossOrigin
    public HttpResponseContent serviceDeploy(@RequestParam("nodeName") String node_name,@RequestParam("account") String account) throws Exception {
        HttpResponseContent response = new HttpResponseContent();
        boolean ans = nodeService.deploy(node_name,account);
        if (ans == false) {
            response.setCode(ResponseEnum.LOGIN_FAILED.getCode());
            response.setMessage(ResponseEnum.LOGIN_FAILED.getMessage());
        } else {
            response.setCode(ResponseEnum.SUCCESS.getCode());
            response.setMessage(ResponseEnum.SUCCESS.getMessage());
        }
        return response;

    }

    @ApiOperation(value = "节点微服务撤销部署")
    @RequestMapping(path = "/close")
    @CrossOrigin
    public HttpResponseContent serviceClose(@RequestParam("nodeName") String node_name,@RequestParam("account") String account) throws Exception {
        HttpResponseContent response = new HttpResponseContent();
        boolean ans = nodeService.close(node_name,account);
        if (ans == false) {
            response.setCode(ResponseEnum.LOGIN_FAILED.getCode());
            response.setMessage(ResponseEnum.LOGIN_FAILED.getMessage());
        } else {
            response.setCode(ResponseEnum.SUCCESS.getCode());
            response.setMessage(ResponseEnum.SUCCESS.getMessage());
        }
        return response;
    }

}
