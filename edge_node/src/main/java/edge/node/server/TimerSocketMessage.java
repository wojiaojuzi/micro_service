package edge.node.server;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
@EnableScheduling
public class TimerSocketMessage {
    /**
     * 推送消息到前台
     */
    @Scheduled(cron = "*/1 * * * * * ")
    public void SocketMessage(){
        Map<String, Object> maps = new HashMap<>();
        maps.put("type", "sendMessage");
        maps.put("data","11111");
        MapServer.sendInfo(maps);
    }
}
