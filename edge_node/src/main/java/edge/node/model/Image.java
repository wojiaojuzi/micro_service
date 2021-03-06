package edge.node.model;

public class Image {
    private String nodeName;

    private String serviceName;

    private String imageId;

    private String imageShortId;

    private String imageRepository;

    private String imageTag;

    private String imageStatus;

    public Image(String nodeName, String serviceName, String imageId, String imageShortId, String imageRepository,
                 String imageTag, String imageStatus){
        this.nodeName = nodeName;
        this.imageId = imageId;
        this.imageRepository = imageRepository;
        this.imageTag = imageTag;
        this.imageShortId = imageShortId;
        this.imageStatus = imageStatus;
        this.serviceName = serviceName;
    }
    public String getNodeName() {
        return this.nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getImageId() {
        return this.imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getimageShortId() {
        return this.imageShortId;
    }

    public void setImageShortId(String imageShortId) {
        this.imageShortId = imageShortId;
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

    public String getImageStatus() {
        return this.imageStatus;
    }

    public void setImageStatus(String imageStatus) {
        this.imageStatus = imageStatus;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
