package device.monitor.model;

import io.swagger.annotations.ApiModelProperty;

public class Device {
    @ApiModelProperty(value = "设备编号")
    private String device_no;

    @ApiModelProperty(value = "设备连接状态")
    private boolean device_status;

    @ApiModelProperty(value = "设备类型")
    private String device_type;

    @ApiModelProperty(value = "设备创建时间")
    private String create_at;

    @ApiModelProperty(value = "所属用户id")
    private String uid;

    @ApiModelProperty(value = "所属用户name")
    private String user_name;

    public void setUser_name(String user_name){ this.user_name = user_name; }
    public String getUser_name(){ return this.user_name; }

    public String getDeviceNo() {
        return this.device_no;
    }
    public void setDeviceNo(String device_no) {
        this.device_no = device_no;
    }

    public void setDeviceStatus(boolean device_status) {
        this.device_status = device_status;
    }
    public boolean getDeviceStatus(){ return this.device_status; }

    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDeviceType() {
        return this.device_type;
    }
    public void setDeviceType(String device_type) {
        this.device_type = device_type;
    }

    public String getCreateAt() {
        return this.create_at;
    }
    public void setCreateAt(String create_at) {
        this.create_at = create_at;
    }


}
