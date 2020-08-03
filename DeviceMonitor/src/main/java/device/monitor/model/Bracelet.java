package device.monitor.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Zhaoone on 2019/11/4
 **/
public class Bracelet {

    @ApiModelProperty(value = "手环编号")
    private String bracelet_no;

    @ApiModelProperty(value = "设备状态")
    private Boolean device_status;

    @ApiModelProperty(value = "绑定手持机编号")
    private String device_no;

    @ApiModelProperty(value = "绑定犯人id")
    private String prisoner_id;

    public String getPrisonerId() {
        return this.prisoner_id;
    }
    public void setPrisonerId(String prisonerId) {
        this.prisoner_id = prisonerId;
    }

    public String getBraceletNo() {
        return this.bracelet_no;
    }
    public void setBraceletNo(String braceletNo) {
        this.bracelet_no = braceletNo;
    }

    public Boolean getDeviceStatus() {
        return this.device_status;
    }
    public void setDeviceStatus(Boolean deviceStatus) {
        this.device_status = deviceStatus;
    }

    public String getDeviceNo() {
        return this.device_no;
    }
    public void setDeviceNo(String deviceNo) {
        this.device_no = deviceNo;
    }
}
