package edge.node.model;

import io.swagger.annotations.ApiModelProperty;

public class CheckVervel {
    @ApiModelProperty(value = "节点编号")
    private String node_id;

    @ApiModelProperty(value = "手环编号")
    private String vervel_no;

    @ApiModelProperty(value = "所属手持机编号")
    private String device_no;

    @ApiModelProperty(value = "脚环状态")
    private boolean vervel_status;

    @ApiModelProperty(value = "绑定犯人编号")
    private String prisoner_id;

    @ApiModelProperty(value = "cpu")
    private String cpu;

    @ApiModelProperty(value = "内存")
    private String memory;

    public String getNode_id() { return this.node_id; }
    public void setNode_id(String node_id) { this.node_id = node_id; }

    public String getVervel_no(){ return this.vervel_no; }
    public void setVervel_no(String vervel_no){ this.vervel_no = vervel_no; }

    public String getDevice_no() { return this.device_no; }
    public void setDevice_no(String device_no) { this.device_no = device_no; }

    public boolean isVervel_status() { return this.vervel_status; }
    public void setVervel_status(boolean vervel_status) { this.vervel_status = vervel_status; }

    public String getPrisoner_id() { return this.prisoner_id; }
    public void setPrisoner_id(String prisoner_id) { this.prisoner_id = prisoner_id; }

    public String getCpu(){ return this.cpu; }
    public void setCpu(String cpu){ this.cpu = cpu; }

    public String getMemory(){ return this.memory; }
    public void setMemory(String memory){ this.memory = memory; }
}
