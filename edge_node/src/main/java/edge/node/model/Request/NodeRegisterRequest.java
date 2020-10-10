package edge.node.model.Request;

public class NodeRegisterRequest {
    private String nodeName;
    private String area;
    private String location;
    private String ip;
    private String cpu;
    private String ferquence;
    private String memory;
    private String lon;
    private String lat;
    private String account;

    public NodeRegisterRequest(String nodeName, String area, String location, String ip, String cpu, String ferquence, String memory, String lon, String lat, String account) {
        this.nodeName = nodeName;
        this.area = area;
        this.location = location;
        this.ip = ip;
        this.cpu = cpu;
        this.ferquence = ferquence;
        this.memory = memory;
        this.lon = lon;
        this.lat = lat;
        this.account = account;
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

    public String getFerquence() {
        return ferquence;
    }

    public void setFerquence(String ferquence) {
        this.ferquence = ferquence;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
