package com.loger.service;


import com.loger.mapper.LogerMapper;
import com.loger.model.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LogerService {
    private final LogerMapper logerMapper;
    private static final String mysqlSdfPatternString = "yyyy-MM-dd HH:mm:ss";

    public LogerService(LogerMapper logerMapper){
        this.logerMapper = logerMapper;
    }

    public void addLog(String account, String detail){
        Date createTime = new Date();
        SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
        Loger log = new Loger();
        log.setAccount(account);
        log.setTime(mysqlSdf.format(createTime));
        log.setDetail(detail);
        logerMapper.create_log(log.getTime(),log.getAccount(),log.getDetail());
    }

    public List<Loger> get_all(){ return logerMapper.get_all(); }
    public List<Loger> get_by_date(String start,String end){ return logerMapper.get_by_date(start,end); }

    public void delete_log(String time){
        logerMapper.delete_log(time);
    }
    public void clear_log(){
        logerMapper.clear_log();
    }

}
