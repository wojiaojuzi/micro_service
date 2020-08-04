package com.manager.model;

import java.io.Serializable;

/**
 * 消息内容载体，在rabbitmq中，存储的消息可以是任意的java类型的对象。
 * 强制要求，作为消息数据载体的类型，必须是Serializable的。
 * 如果消息数据载体类型未实现Serializable，在收发消息的时候，都会有异常发生。
 */
public class MsgInfo implements Serializable {
    private String logType;
    private String createTime;
    private String userName;
    private String detail;

    public MsgInfo(String logType, String createTime, String userName, String detail) {
        this.logType = logType;
        this.createTime = createTime;
        this.userName = userName;
        this.detail = detail;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "MsgInfo{" +
                "logType='" + logType + '\'' +
                ", createTime='" + createTime + '\'' +
                ", userName='" + userName + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
