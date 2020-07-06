package edge.node.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Zhaoone on 2019/10/24
 **/
public class DeviceAndTask {
    @ApiModelProperty(value = "手持机信息")
    private Device device;

    @ApiModelProperty(value = "任务信息")
    private Task task;

    public Device getDevice() {
        return this.device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Task getTask() {
        return this.task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}
