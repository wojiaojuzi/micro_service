package edge.node.model;

public class Container {
    private String nodeName;

    private String serviceName;

    private String serviceStatus;

    private String containerName;

    private String containerId;

    private String containerShortId;

    private String containerStatus;

    private boolean isCreated;



    public Container(String nodeName, String serviceName, String serviceStatus,
                     String containerName, String containerId, String containerShortId,
                     String containerStatus, boolean isCreated) {
        this.nodeName = nodeName;
        this.serviceName = serviceName;
        this.serviceStatus = serviceStatus;
        this.containerName = containerName;
        this.containerId = containerId;
        this.containerShortId = containerShortId;
        this.containerStatus = containerStatus;
        this.isCreated = isCreated;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceStatus() {
        return this.serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getContainerStatus() {
        return this.containerStatus;
    }

    public void setContainerStatus(String containerStatus) {
        this.containerStatus = containerStatus;
    }

    public String getContainerName() {
        return this.containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public boolean getIsCreated() {
        return this.isCreated;
    }

    public void setIsCreated(boolean isCreated) {
        this.isCreated = isCreated;
    }

    public String getContainerId() {
        return this.containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getContainerShortId() {
        return this.containerShortId;
    }

    public void setContainerShortId(String containerShortId) {
        this.containerShortId = containerShortId;
    }
}
