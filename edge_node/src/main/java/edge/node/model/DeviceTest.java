package edge.node.model;

public class DeviceTest {
    private String nodeName;

    private String deviceId;

    private String deviceStatus;

    private String deviceType;

    private String cpuRate;

    private String memoryRate;

    private String batteryRate;

    public DeviceTest(String nodeName, String deviceId, String deviceStatus, String deviceType,
                      String cpuRate,String memoryRate,String batteryRate) {
        this.nodeName = nodeName;
        this.deviceId = deviceId;
        this.deviceStatus = deviceStatus;
        this.deviceType = deviceType;
        this.cpuRate = cpuRate;
        this.memoryRate = memoryRate;
        this.batteryRate = batteryRate;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceStatus() {
        return this.deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getCpuRate() {
        return this.cpuRate;
    }

    public void setCpuRate(String cpuRate) {
        this.cpuRate = cpuRate;
    }

    public String getMemoryRate() {
        return this.memoryRate;
    }

    public void setMemoryRate(String memoryRate) {
        this.memoryRate = memoryRate;
    }

    public String getBatteryRate() {
        return this.batteryRate;
    }

    public void setBatteryRate(String batteryRate) {
        this.batteryRate = batteryRate;
    }
}
