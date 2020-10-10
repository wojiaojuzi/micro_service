package edge.node.model;

import java.sql.Timestamp;

public class Node {
    private String nodeName;

    private String area;

    private String location;

    private String lon;

    private String lat;

    private String ip;

    private String cpu;

    private double cpuRate;

    private String memory;

    private double memRate;

    private String frequence;

    private boolean nodeStatus;

    private String nodeCreateAt;

    public Node(String nodeName, String area, String location, String lon, String lat, String ip, String cpu, double cpuRate, String memory, double memRate, String frequence, boolean nodeStatus, String nodeCreateAt) {
        this.nodeName = nodeName;
        this.area = area;
        this.location = location;
        this.lon = lon;
        this.lat = lat;
        this.ip = ip;
        this.cpu = cpu;
        this.cpuRate = cpuRate;
        this.memory = memory;
        this.memRate = memRate;
        this.frequence = frequence;
        this.nodeStatus = nodeStatus;
        this.nodeCreateAt = nodeCreateAt;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public double getCpuRate() {
        return cpuRate;
    }

    public void setCpuRate(double cpuRate) {
        this.cpuRate = cpuRate;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public double getMemRate() {
        return memRate;
    }

    public void setMemRate(double memRate) {
        this.memRate = memRate;
    }

    public String getFrequence() {
        return frequence;
    }

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }

    public boolean isNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(boolean nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public String getNodeCreateAt() {
        return nodeCreateAt;
    }

    public void setNodeCreateAt(String nodeCreateAt) {
        this.nodeCreateAt = nodeCreateAt;
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeName='" + nodeName + '\'' +
                ", area='" + area + '\'' +
                ", location='" + location + '\'' +
                ", lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                ", ip='" + ip + '\'' +
                ", cpu='" + cpu + '\'' +
                ", cpuRate=" + cpuRate +
                ", memory='" + memory + '\'' +
                ", memRate=" + memRate +
                ", frequence='" + frequence + '\'' +
                ", nodeStatus=" + nodeStatus +
                ", nodeCreateAt='" + nodeCreateAt + '\'' +
                '}';
    }
}
