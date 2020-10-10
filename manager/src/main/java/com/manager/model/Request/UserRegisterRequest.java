package com.manager.model.Request;

/**
 * @Author: jojo
 * @Description: 用户注册请求body格式
 * @Date: Created on 2019/4/11 21:26
 */
public class UserRegisterRequest {

    private String account;
    private String password;
    private String email;
    private String position;
    private String ministry;

    public UserRegisterRequest(String account, String password, String email, String position, String ministry) {
        this.account = account;
        this.password = password;
        this.email = email;
        this.position = position;
        this.ministry = ministry;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMinistry() {
        return ministry;
    }

    public void setMinistry(String ministry) {
        this.ministry = ministry;
    }

    @Override
    public String toString() {
        return "UserRegisterRequest{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", ministry='" + ministry + '\'' +
                '}';
    }
}
