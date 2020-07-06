package edge.node.service;


import edge.node.mapper.NodeMapper;
import edge.node.model.Node;
import edge.node.model.api.AdminFeign;
import edge.node.model.api.LogFeign;
import edge.node.model.return_location;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NodeService {
    private static final String mysqlSdfPatternString = "yyyy-MM-dd HH:mm:ss";
    private final NodeMapper nodeMapper;
    private final LogFeign logFeign;
    private final AdminFeign adminFeign;

    public NodeService(NodeMapper nodeMapper, LogFeign logFeign, AdminFeign adminFeign){
        this.nodeMapper = nodeMapper;
        this.logFeign = logFeign;
        this.adminFeign = adminFeign;
    }

    //节点注册逻辑要求改
    public Node nodeRegister(Node node,String account){
        Date createTime = new Date();
        SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
        node.setNodeCreateAt(mysqlSdf.format(createTime));

        nodeMapper.create_node(node.getNode_name(),node.getLocation(),
                                node.getNode_status(),node.getNodeCreateAt(),
                                node.getRunAt(),node.getEndLastAt(),
                                node.getCpu(), node.getMemory());
        Node test = nodeMapper.getNodeByNodeName(node.getNode_name());
        if(test == null)
            return null;
        else {
            String admin_name = adminFeign.getAdminNameByAccount(account);
            logFeign.addLog(account,admin_name,"注册节点:  节点名="+node.getNode_name()+" , 位置="+node.getLocation());
            return test;
        }
    }

    //删除节点逻辑要修改
    public void nodeDelete(String node_name,String account){
        nodeMapper.deleteNodeByNodeName(node_name);
        String admin_name = adminFeign.getAdminNameByAccount(account);
        logFeign.addLog(account,admin_name,"删除节点:  节点名="+node_name);
    }

    public Node nodeGet(String node_name){ return nodeMapper.getNodeByNodeName(node_name);}

    public int get_on_num(){
        return nodeMapper.get_on_num().size();
    }

    public List<Node> get_all(){ return nodeMapper.get_all(); }

    public List<return_location> get_all_location(){
        List<String> location = nodeMapper.get_all_location();
        List<return_location> ans = new ArrayList<>();
        for(int i=0;i<location.size();i++){
            return_location p = new return_location();
            p.setLocation(location.get(i));
            p.setValue(20);
            ans.add(p);
        }
        return ans;
    }

    public List<return_location> get_on_location(){
        List<String> location = nodeMapper.get_on_location();
        List<return_location> ans = new ArrayList<>();
        for(int i=0;i<location.size();i++) {
            return_location p = new return_location();
            p.setLocation(location.get(i));
            p.setValue(20);
            ans.add(p);
        }
        return ans;
    }

    public List<return_location> get_off_location(){
        List<String> location = nodeMapper.get_off_location();
        List<return_location> ans = new ArrayList<>();
        for(int i=0;i<location.size();i++) {
            return_location p = new return_location();
            p.setLocation(location.get(i));
            p.setValue(20);
            ans.add(p);
        }
        return ans;
    }

    public boolean deploy(String node_name,String account) throws IOException {
        boolean ans = nodeMapper.geNodeStatusByNodeName(node_name);
        if(ans == true){
            return false;
        }
        else {
            String id = nodeMapper.getNodeByNodeName(node_name).getId();
            File dir = new File("D:\\micro_service\\deploy");
            System.out.println(dir+" open.py "+id);
            Runtime.getRuntime().exec("python open.py "+id,null,dir);
            Date createTime = new Date();
            SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
            nodeMapper.updateNodeStatusByNodeName(true, node_name);
            nodeMapper.updateRunAtByNodeName(mysqlSdf.format(createTime),node_name);

            String admin_name = adminFeign.getAdminNameByAccount(account);
            logFeign.addLog(account,admin_name,"微服务部署:  节点名="+node_name);
            return true;
        }
    }

    public boolean close(String node_name,String account) throws IOException {
        boolean ans = nodeMapper.geNodeStatusByNodeName(node_name);
        if(ans == false){
            return false;
        }
        else {
            String id = nodeMapper.getNodeByNodeName(node_name).getId();
            File dir = new File("D:\\micro_service\\deploy");
            System.out.println(dir+" close.py "+id);
            Runtime.getRuntime().exec("python close.py "+id,null,dir);
            Date createTime = new Date();
            SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
            nodeMapper.updateNodeStatusByNodeName(false, node_name);
            nodeMapper.updateEndLastAtByNodeName(mysqlSdf.format(createTime),node_name);
            nodeMapper.updateRunAtByNodeName("",node_name);

            String admin_name = adminFeign.getAdminNameByAccount(account);
            logFeign.addLog(account,admin_name,"取消微服务部署:  节点名="+node_name);
            return true;
        }
    }
}
