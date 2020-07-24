package edge.node.server;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//定时任务类 用于周期性地发起请求
@Component
@EnableScheduling
public class PushSchedule {

    @Scheduled(fixedRate = 2000)
    private void pushImage(){

    }

}
