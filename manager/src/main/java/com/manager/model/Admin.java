package com.manager.model;
import io.swagger.annotations.ApiModelProperty;

public class Admin {

    @ApiModelProperty(value = "管理员id")
    private String id;

    @ApiModelProperty(value = "管理员账号")
    private String account;

    @ApiModelProperty(value = "管理员密码")
    private String password;

    @ApiModelProperty(value = "管理员姓名")
    private String adminName;

    @ApiModelProperty(value = "管理员创建日期")
    private String createAt;

    @ApiModelProperty(value = "登录token")
    private String LoginToken;

    @ApiModelProperty(value = "登录token")
    private String tokenCreateAt;

    public String getTokenCreateAt() {
        return tokenCreateAt;
    }

    public void setTokenCreateAt(String tokenCreateAt) {
        this.tokenCreateAt = tokenCreateAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return LoginToken;
    }

    public void setToken(String Token) {
        this.LoginToken = Token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAccount() { return account; }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setCreateAt(String createAt){ this.createAt = createAt; }
    public String getCreateAt(){ return this.createAt; }
}
