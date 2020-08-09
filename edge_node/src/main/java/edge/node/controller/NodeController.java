package edge.node.controller;


import edge.node.model.Node;
import edge.node.model.Response.HttpResponseContent;
import edge.node.model.Response.ResponseEnum;
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
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    @CrossOrigin
    public HttpResponseContent nodeRegister(@RequestParam("nodeName") String node_name,
                                            @RequestParam("account") String account,
                                            @RequestParam("cpu") String cpu,
                                            @RequestParam("memory") String memory,
                                            @RequestParam("ip") String ip,
                                            @RequestParam("remark") String remark){
        HttpResponseContent response = new HttpResponseContent();
        Node node = new Node(node_name, "","","",false,"","","",cpu, 0,memory,0,ip, remark);
        Node res = nodeService.nodeRegister(node,account);
        if (res == null) {
            response.setCode(ResponseEnum.ERROR.getCode());
            response.setMessage(ResponseEnum.ERROR.getMessage());
        } else {
            response.setCode(ResponseEnum.SUCCESS.getCode());
            response.setMessage(ResponseEnum.SUCCESS.getMessage());
            response.setData(res);
        }
        return response;
    }

    @ApiOperation(value = "节点删除")
    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    @CrossOrigin
    public HttpResponseContent nodeDelete(@RequestParam("nodeName") String nodeName, @RequestParam("account") String account) {
        HttpResponseContent response = new HttpResponseContent();
        if(nodeService.nodeDelete(nodeName,account)) {
            response.setCode(ResponseEnum.SUCCESS.getCode());
            response.setMessage(ResponseEnum.SUCCESS.getMessage());
        }
        else {
            response.setCode(ResponseEnum.ERROR.getCode());
            response.setMessage(ResponseEnum.ERROR.getMessage());
        }

        return response;
    }

    @ApiOperation(value = "查看节点信息")
    @RequestMapping(path = "/get", method = RequestMethod.GET)
    @CrossOrigin
    public Node nodeGet(@RequestParam("nodeName") String nodeName) {
        return nodeService.nodeGet(nodeName);
    }

    @ApiOperation(value = "查看所有节点")
    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    @CrossOrigin
    public List<Node> get_all() {
        return nodeService.get_all();
    }


}
