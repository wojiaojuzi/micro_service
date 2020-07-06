package com.loger.model;

public class Loger {
    private String time;//时间

    private String account;//管理员账号

    private String admin_name;//管理员姓名

    private String detail;//时间详情


    public void setTime(String time){ this.time = time; }
    public String getTime(){ return this.time; }

    public void setAccount(String account){ this.account = account; }
    public String getAccount(){ return this.account; }

    public void setAdmin_name(String admin_name){ this.admin_name = admin_name; }
    public String getAdmin_name(){ return this.admin_name; }

    public void setDetail(String detail){ this.detail = detail; }
    public String getDetail(){ return this.detail; }
}
