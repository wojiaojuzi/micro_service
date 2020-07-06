package com.manager.controller;

import com.manager.model.Admin;
import com.manager.model.Response.HttpResponseContent;
import com.manager.model.Response.ResponseEnum;
import com.manager.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Zhaoone on 2019/10/22
 **/

@Controller
@RequestMapping(path = "/admins")
@EnableAutoConfiguration
@Api(tags = "Admin", description = "管理员相关操作")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @ApiOperation(value = "管理员登陆")
    @RequestMapping(path = "/login")
    @ResponseBody
    //@PostMapping(path = "/login")
    public HttpResponseContent adminLogin(@RequestParam(value="account",required=false) String account, @RequestParam(value="password",required=false) String password)
            throws Exception {
        HttpResponseContent response = new HttpResponseContent();
        Admin admin = adminService.adminLogin(account,password);
        if (admin == null) {
            response.setCode(ResponseEnum.LOGIN_FAILED.getCode());
            response.setMessage(ResponseEnum.LOGIN_FAILED.getMessage());
        } else {
            response.setCode(ResponseEnum.SUCCESS.getCode());
            response.setMessage(ResponseEnum.SUCCESS.getMessage());
            response.setData(admin);
        }
        return response;
    }

    @ApiOperation(value = "管理员登出")
    @RequestMapping(path = "/logout")
    @ResponseBody
    public HttpResponseContent adminLogout(@RequestParam(value = "token",required=false) String token) throws Exception {
        HttpResponseContent response = new HttpResponseContent();
        adminService.adminLogout(token);
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());
        return response;
    }

    @ApiOperation(value = "管理员注册")
    @RequestMapping(path = "/register")
    @ResponseBody
    public HttpResponseContent adminRegister(@RequestParam(value = "account",required=false) String account,
                                             @RequestParam(value = "password",required=false) String password,
                                             @RequestParam(value = "adminName",required=false) String adminName)
            throws Exception {
        HttpResponseContent response = new HttpResponseContent();
        if(adminService.adminRegister(account,password,adminName)=="error"){
            response.setCode(ResponseEnum.LOGIN_FAILED.getCode());
            response.setMessage(ResponseEnum.LOGIN_FAILED.getMessage());
        }
        else {
            response.setCode(ResponseEnum.SUCCESS.getCode());
            response.setMessage(ResponseEnum.SUCCESS.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "管理员信息")
    @RequestMapping(path = "/getAboutInformation")
    @ResponseBody
    public Admin aboutInformation(@RequestParam(value = "token") String token){
        return adminService.aboutInformationByToken(token);
    }


    /*@ApiOperation(value = "修改昵称")
    @RequestMapping(path = "/getAboutInformation", method = RequestMethod.GET)
    @ResponseBody
    public Admin alterAdminName(@RequestParam(value = "token") String token,RequestParam(value = "adminName") String adminName) {
        String adminid = adminService.getAccountByToken();
        Admin admin = adminService.aboutInformation(adminid);
    }*/

    @ApiOperation(value = "修改密码")
    @RequestMapping(path = "/alterpassword")
    @ResponseBody
    public HttpResponseContent alterPassword(@RequestParam(value = "account",required=false) String account,
                                             @RequestParam(value = "oldpassword",required=false) String oldpassword,
                                             @RequestParam(value = "newpassword",required=false) String newpassword)
        throws Exception{
        HttpResponseContent response = new HttpResponseContent();
        if(adminService.alterPassword(account,oldpassword,newpassword)=="error"){
            response.setCode(ResponseEnum.LOGIN_FAILED.getCode());
            response.setMessage(ResponseEnum.LOGIN_FAILED.getMessage());
        }
        else{
            Admin admin = adminService.aboutInformationByAccount(account);
            response.setCode(ResponseEnum.SUCCESS.getCode());
            response.setMessage(ResponseEnum.SUCCESS.getMessage());
            response.setData(admin);
        }
        return response;
    }

    @RequestMapping("/getAdminNameByAccount")
    @ResponseBody
    public String getAdminNameByAccount(@RequestParam("account")String account){
        return adminService.getAdminNameByAccount(account);
    }
}
