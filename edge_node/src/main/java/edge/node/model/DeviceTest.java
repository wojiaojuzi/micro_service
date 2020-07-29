package edge.node.model;

public class DeviceTest {
    private String nodeName;

    private String deviceId;

    private String deviceStatus;

    private String deviceType;

    public DeviceTest(String nodeName, String deviceId, String deviceStatus, String deviceType) {
        this.nodeName = nodeName;
        this.deviceId = deviceId;
        this.deviceStatus = deviceStatus;
        this.deviceType = deviceType;
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
}
