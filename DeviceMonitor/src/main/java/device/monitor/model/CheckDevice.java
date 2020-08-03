package device.monitor.model;

import io.swagger.annotations.ApiModelProperty;

public class CheckDevice {
    @ApiModelProperty(value = "节点编号")
    private String node_id;

    @ApiModelProperty(value = "设备编号")
    private String device_no;

    @ApiModelProperty(value = "设备类型")
    private String device_type;

    @ApiModelProperty(value = "押解人ID")
    private String user_id;

    @ApiModelProperty(value = "押解人姓名")
    private String user_name;

    @ApiModelProperty(value = "设备状态")
    private boolean device_status;

    @ApiModelProperty(value = "cpu")
    private String cpu;

    @ApiModelProperty(value = "内存")
    private String memory;

    @ApiModelProperty(value = "任务编号")
    private String task_no;

    @ApiModelProperty(value = "任务详情")
    private String detail;

    @ApiModelProperty(value = "犯人姓名")
    private String prisoner_name;

    @ApiModelProperty(value = "车牌号")
    private String car_no;



    public String getNode_id() { return this.node_id; }

    public void setNode_id(String node_id) { this.node_id = node_id; }

    public String getDevice_no() { return this.device_no; }

    public void setDevice_no(String device_no) { this.device_no = device_no; }

    public String getDevice_type() { return this.device_type; }

    public void setDevice_type(String device_type) { this.device_type = device_type; }

    public String getUser_id() { return this.user_id; }

    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getUser_name() { return this.user_name; }

    public void setUser_name(String user_name) { this.user_name = user_name; }

    public boolean isDevice_status() { return this.device_status; }

    public void setDevice_status(boolean device_status) { this.device_status = device_status; }

    public String getCpu() { return this.cpu; }

    public void setCpu(String cpu) { this.cpu = cpu; }

    public String getMemory() { return this.memory; }

    public void setMemory(String memory) { this.memory = memory; }

    public String getTask_no(){ return this.task_no; }
    public void setTask_no(String task_no){ this.task_no = task_no; }

    public String getDetail(){ return this.detail; }
    public void setDetail(String detail){ this.detail = detail; }

    public String getPrisoner_name(){ return this.prisoner_name; }
    public void setPrisoner_name(String prisoner_name){ this.prisoner_name = prisoner_name; }

    public String getCar_no(){ return this.car_no; }
    public void setCar_no(String car_no){ this.car_no = car_no; }

}
