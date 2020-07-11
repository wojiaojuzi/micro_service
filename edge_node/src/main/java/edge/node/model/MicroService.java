package edge.node.model;

public class MicroService {
    private String serviceName;

    private String serviceStatus;

    private String nodeName;

    private String imageRepository;

    private String imageTag;

    private boolean imageStatus;

    private String containerName;

    private String containerStatus;


    public MicroService(String serviceName, String serviceStatus, String nodeName, String imageRepository, String imageTag, boolean imageStatus, String containerName, String containerStatus) {
        this.serviceName = serviceName;
        this.serviceStatus = serviceStatus;
        this.nodeName = nodeName;
        this.imageRepository = imageRepository;
        this.imageTag = imageTag;
        this.imageStatus = imageStatus;
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

    public boolean isImageStatus() {
        return this.imageStatus;
    }

    public void setImageStatus(boolean imageStatus) {
        this.imageStatus = imageStatus;
    }

    public String getContainerStatus() {
        return this.containerStatus;
    }

    public void setContainerStatus(String containerStatus) {
        this.containerStatus = containerStatus;
    }

    public String getImageRepository() {
        return this.imageRepository;
    }

    public void setImageRepository(String imageRepository) {
        this.imageRepository = imageRepository;
    }

    public String getImageTag() {
        return this.imageTag;
    }

    public void setImageTag(String imageTag) {
        this.imageTag = imageTag;
    }

    public String getContainerName() {
        return this.containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }
}
