package edge.node.model;

public class Container {
    private String nodeName;

    private String serviceName;

    private String serviceStatus;

    private String containerName;

    private String containerStatus;



    public Container(String nodeName, String serviceName, String serviceStatus,
                     String containerName, String containerStatus) {
        this.serviceName = serviceName;
        this.serviceStatus = serviceStatus;
        this.nodeName = nodeName;
        this.containerName = containerName;
        this.containerStatus = containerStatus;
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
}
