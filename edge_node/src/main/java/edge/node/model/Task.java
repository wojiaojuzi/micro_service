package edge.node.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Zhaoone on 2019/10/22
 **/
public class Task {

    @ApiModelProperty(value = "任务编号")
    private String taskNo;

    @ApiModelProperty(value = "犯人姓名")
    private String prisonerName;

    @ApiModelProperty(value = "车牌号")
    private String carNo;

    @ApiModelProperty(value = "押解人姓名")
    private String userName;

    @ApiModelProperty(value = "任务详情")
    private String detail;

    public String getTaskNo() {
        return this.taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getPrisonerName() { return this.prisonerName; }

    public void setPrisonerName(String prisonerName) {
        this.prisonerName = prisonerName;
    }

    public String getCarNo() { return this.carNo; }

    public void setCarNo(String carNo) { this.carNo = carNo; }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
