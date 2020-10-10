package com.manager.model.Request;

/**
 * @Author: jojo
 * @Description: 用户登陆请求格式
 * @Date: Created on 2019/4/4 21:55
 */
/**
 * @Author: zhaoone
 * @Description:添加功能
 * @Date: Created on 2019/10/14
 */
public class LoginRequest {

    private String account;
    private String password;

    public LoginRequest(String account, String password) {
        this.account = account;
        this.password = password;
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
}
