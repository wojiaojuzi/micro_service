package edge.node.model;

import com.netflix.discovery.provider.ISerializer;

import java.io.Serializable;
import java.util.List;

public class deployBody implements Serializable {
    private  String nodeName;

    private List<String> serviceName;

    private String account;

    public String getNodeName() {
        return this.nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public List<String> getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(List<String> serviceName) {
        this.serviceName = serviceName;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
