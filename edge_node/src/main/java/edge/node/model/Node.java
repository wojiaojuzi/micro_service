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

    private String remark;

    public Node(String nodeName, String location, String lon, String lat, boolean nodeStatus, String nodeCreateAt, String runAt, String endLastAt, String cpu, double cpuRate, String memory, double memRate, String ip, String remark) {
        this.nodeName = nodeName;
        this.location = location;
        this.lon = lon;
        this.lat = lat;
        this.nodeStatus = nodeStatus;
        this.nodeCreateAt = nodeCreateAt;
        this.runAt = runAt;
        this.endLastAt = endLastAt;
        this.ip = ip;
        this.cpu = cpu;
        this.cpuRate = cpuRate;
        this.memory = memory;
        this.memRate = memRate;
        this.remark = remark;
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

    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
