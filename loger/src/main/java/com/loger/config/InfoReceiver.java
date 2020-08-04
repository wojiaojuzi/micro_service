package com.loger.config;

import com.alibaba.fastjson.JSONObject;
import com.loger.mapper.LogerMapper;
import com.loger.model.MsgInfo;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
/*@RabbitListener(
        bindings=@QueueBinding(
                value=@Queue(value="log",autoDelete="true"),
                exchange=@Exchange(value="log",type= ExchangeTypes.TOPIC),
                key="logInfo"
        )
)*/
public class InfoReceiver {
    private final LogerMapper logerMapper;

    public InfoReceiver(LogerMapper logerMapper){
        this.logerMapper = logerMapper;
    }

    //@RabbitHandler
    @RabbitListener(
            bindings=@QueueBinding(
                    value=@Queue(value="log",autoDelete="false"),
                    exchange=@Exchange(value="log",type= ExchangeTypes.TOPIC),
                    key="logInfo"
            )
    )
    public void process(JSONObject jsonObject){
        //JSONObject jsonObject = (JSONObject)JSONObject.toJSON(msg);
        MsgInfo msgInfo = JSONObject.toJavaObject(jsonObject,MsgInfo.class);
        System.out.println("......Info........receiver: "+msgInfo);
        if(msgInfo.getLogType().equals("eventLog")){
            logerMapper.create_log(msgInfo.getCreateTime(), msgInfo.getUserName(), msgInfo.getDetail());
        }
        else if(msgInfo.getLogType().equals("httpLog")){
            try {
                Date nowDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = dateFormat.format(nowDate);
                String subDate = format.substring(0, 10);
                String pathName = "./loger/src/main/resources/logFile/"+subDate+".txt";
                File file = new File(pathName);
                if(!file.exists()){
                    file.createNewFile();
                }

                String time = msgInfo.getCreateTime();
                String userName = msgInfo.getUserName();
                String detail = msgInfo.getDetail();
                String content = "["+time+"] ["+userName+"] ["+detail+"]\n";
                //System.out.println(content);

                FileWriter fileWriter = new FileWriter(file,true);
                BufferedWriter bw = new BufferedWriter(fileWriter);
                bw.write(content);
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
