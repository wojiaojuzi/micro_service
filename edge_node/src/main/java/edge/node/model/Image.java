package edge.node.model;

public class Image {
    private String nodeName;

    private String imageId;

    private String imageShortId;

    private String imageTag;

    private boolean imageStatus;
    public Image(String nodeName, String imageId, String imageShortId, String imageTag, boolean imageStatus){
        this.nodeName = nodeName;
        this.imageId = imageId;
        this.imageTag = imageTag;
        this.imageShortId = imageShortId;
        this.imageStatus = imageStatus;
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

    public void setimageShortId(String imageShortId) {
        this.imageShortId = imageShortId;
    }

    public String getImageTag() {
        return this.imageTag;
    }

    public void setImageTag(String imageTag) {
        this.imageTag = imageTag;
    }

    public boolean isImageStatus() {
        return this.imageStatus;
    }

    public void setImageStatus(boolean imageStatus) {
        this.imageStatus = imageStatus;
    }
}
