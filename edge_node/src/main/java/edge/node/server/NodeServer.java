package edge.node.server;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket/nodesocket")
@Component
public class NodeServer {
    private static Logger log = LoggerFactory.getLogger(MapServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<NodeServer> webSocketSet = new CopyOnWriteArraySet<NodeServer>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        log.info("有新窗口开始监听,当前在线人数为" + getOnlineCount());
        try {
            sendMessage("连接成功");
        } catch (Exception e) {
            log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        try {
            webSocketSet.remove(this);  //从set中删除
            subOnlineCount();           //在线数减1
            session.close();
            log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到的信息:"+message);
        Map<String, Object> maps = new HashMap<>();
        maps.put("type", message);
        this.sendInfo(maps);
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(Object obj)  {
        try {
            synchronized (this.session) {
                this.session.getBasicRemote().sendText((JSON.toJSONString(obj)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 群发自定义消息
     * */
    public static void sendInfo(Object obj) {
        for (NodeServer item : webSocketSet) {
            try {
                item.sendMessage(obj);
            } catch (Exception e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        NodeServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        NodeServer.onlineCount--;
    }
}

