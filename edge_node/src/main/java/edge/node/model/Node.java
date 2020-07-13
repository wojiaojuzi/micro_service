package edge.node.model;

import java.sql.Timestamp;

public class Node {
    private String nodeName;

    private String location;

    private String lon;

    private String lat;

    private boolean nodeStatus;

    private String nodeCreateAt;

    private String runAt;

    private String endLastAt;

    private String ip;

    private String cpu;

    private double cpuRate;

    private String memory;

    private double memRate;

    public Node(String nodeName, String cpu, String memory, String ip){
        this.nodeName = nodeName;
        this.cpu = cpu;
        this.memory = memory;
        this.ip = ip;
    }


    /*-------------------------*/
    public String getLocation(){ return this.location; }
    public void setLocation(String location){ this.location = location; }

    public String getLon() {
        return this.lon;
    }
    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return this.lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getNodeName() { return this.nodeName; }
    public void setNodeName(String nodeName) { this.nodeName = nodeName; }

    public boolean getNodeStatus() { return this.nodeStatus; }
    public void setNodeStatus(boolean nodeStatus) { this.nodeStatus = nodeStatus; }

    public String getNodeCreateAt() { return this.nodeCreateAt; }
    public void setNodeCreateAt(String nodeCreateAt) { this.nodeCreateAt = nodeCreateAt; }

    public String getRunAt() { return this.runAt; }
    public void setRunAt(String runAt) { this.runAt = runAt; }

    public String getEndLastAt() { return this.endLastAt; }
    public void setEndLastAt(String endLastAt) { this.endLastAt = endLastAt; }

    public String getCpu(){ return this.cpu; }
    public void setCpu(String cpu){ this.cpu = cpu; }

    public String getMemory(){ return this.memory; }
    public void setMemory(String memory){ this.memory = memory; }

    public String getIp(){ return this.ip; }
    public void setIp(String ip){ this.ip = ip; }

    public double getCpuRate() {
        return this.cpuRate;
    }

    public void setCpuRate(double cpuRate) {
        this.cpuRate = cpuRate;
    }

    public double getMemRate() {
        return this.memRate;
    }

    public void setMemRate(double memRate) {
        this.memRate = memRate;
    }

}
