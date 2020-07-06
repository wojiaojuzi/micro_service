package com.loger.controller;

import com.loger.model.Loger;
import com.loger.service.LogerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/log")
@EnableAutoConfiguration
@Api(tags = "Log", description = "日志操作")
public class LogerController {
    private final LogerService logerService;

    public LogerController(LogerService logerService){
        this.logerService = logerService;
    }

    @ApiOperation(value = "添加日志记录")
    @RequestMapping(path = "/addLog")
    @CrossOrigin
    public void addLog(@RequestParam("account") String account,
                       @RequestParam("admin_name")String admin_name,
                       @RequestParam("detail")String detail) {
        logerService.addLog(account,admin_name,detail);
    }

    @ApiOperation(value = "获取所有日志记录")
    @RequestMapping(path = "/getLog")
    @CrossOrigin
    public List<Loger> getLog(){
        return logerService.get_all();
    }

    @ApiOperation(value = "获取特定日期范围日志记录")
    @RequestMapping(path = "/getLogByDate")
    @CrossOrigin
    public List<Loger> getLogByDate(@RequestParam("start")String start,@RequestParam("end")String end){
        return logerService.get_by_date(start,end);
    }

    @ApiOperation(value = "删除一条日志记录")
    @RequestMapping(path = "/deleteLog")
    @CrossOrigin
    public void deleteLog(@RequestParam("time")String time){
        logerService.delete_log(time);
    }

    @ApiOperation(value = "删除所有日志记录")
    @RequestMapping(path = "/clearLog")
    @CrossOrigin
    public void clearLog(){
        logerService.clear_log();
    }
}
