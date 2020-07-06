package edge.node.model;

import io.swagger.annotations.ApiModelProperty;

public class CheckBracelet {
    @ApiModelProperty(value = "节点编号")
    private String node_id;

    @ApiModelProperty(value = "手环编号")
    private String bracelet_no;

    @ApiModelProperty(value = "所属手持机编号")
    private String device_no;

    @ApiModelProperty(value = "手环状态")
    private boolean bracelet_status;

    @ApiModelProperty(value = "绑定犯人编号")
    private String prisoner_id;

    @ApiModelProperty(value = "cpu")
    private String cpu;

    @ApiModelProperty(value = "内存")
    private String memory;

    public String getNode_id() { return this.node_id; }
    public void setNode_id(String node_id) { this.node_id = node_id; }

    public String getBracelet_no() { return this.bracelet_no; }
    public void setBracelet_no(String bracelet_no) { this.bracelet_no = bracelet_no; }

    public String getDevice_no() { return this.device_no; }
    public void setDevice_no(String device_no) { this.device_no = device_no; }

    public boolean isBracelet_status() { return this.bracelet_status; }
    public void setBracelet_status(boolean bracelet_status) { this.bracelet_status = bracelet_status; }

    public String getPrisoner_id() { return this.prisoner_id; }
    public void setPrisoner_id(String prisoner_id) { this.prisoner_id = prisoner_id; }

    public String getCpu(){ return this.cpu; }
    public void setCpu(String cpu){ this.cpu = cpu; }

    public String getMemory(){ return this.memory; }
    public void setMemory(String memory){ this.memory = memory; }
}
