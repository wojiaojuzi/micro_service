package edge.node.model;

import java.sql.Timestamp;

public class Node {

    private String id;

    private String node_name;

    private String location;

    private boolean nodeStatus;

    private String nodeCreateAt;

    private String runAt;

    private String endLastAt;

    private String ip;

    private String cpu;

    private String memory;

    public Node(String node_name, String location, String cpu, String memory/*, String ip*/){
        this.node_name = node_name;
        this.location = location;
        this.cpu = cpu;
        this.memory = memory;
        //this.ip = ip;
    }


    /*-------------------------*/
    public String getLocation(){ return this.location; }
    public void setLocation(String location){ this.location = location; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNode_name() { return node_name; }
    public void setNode_name(String node_name) { this.node_name = node_name; }

    public boolean getNode_status() { return nodeStatus; }
    public void setNode_status(boolean node_status) { this.nodeStatus = node_status; }

    public String getNodeCreateAt() { return nodeCreateAt; }
    public void setNodeCreateAt(String nodeCreateAt) { this.nodeCreateAt = nodeCreateAt; }

    public String getRunAt() { return runAt; }
    public void setRunAt(String runAt) { this.runAt = runAt; }

    public String getEndLastAt() { return endLastAt; }
    public void setEndLastAt(String endLastAt) { this.endLastAt = endLastAt; }

    public String getCpu(){ return this.cpu; }
    public void setCpu(String cpu){ this.cpu = cpu; }

    public String getMemory(){ return this.memory; }
    public void setMemory(String memory){ this.memory = memory; }

    public String getIp(){ return this.ip; }
    public void setIp(String ip){ this.ip = ip; }

}
