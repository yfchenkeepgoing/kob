package com.kob.backend.consumer;

import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
// 映射到特定的链接，websocket也是用jwt验证的
@ServerEndpoint("/websocket/{token}")  // 注意不要以'/'结尾，否则会报异常
public class WebSocketServer {
    // 存储所有链接
    // 通过用户id找到对应的session，然后利用session向前端发送请求
    // 对所有实例可见，定义为静态变量
    // 由于每个线程在每个实例里面，公共的变量必须是线程安全的
    private static ConcurrentHashMap<Integer, WebSocketServer> users = new ConcurrentHashMap<>();

    // 存储每个用户的信息，存储在session中
    private User user;
    private Session session = null;

    // 注入数据库
    private static UserMapper userMapper;
    public void setUserMapper(UserMapper userMapper) {
        WebSocketServer.userMapper = userMapper;
    }

    // 建立连接时自动触发以下函数
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        this.session = session; // 存下session
        System.out.println("connected!"); // 调试信息
        // 假设token就是用户id
        Integer userId = Integer.parseInt(token);
        this.user = userMapper.selectById(userId);
        // 获取用户后存储用户
        users.put(userId, this);
    }

    // 关闭链接时自动调用以下函数
    @OnClose
    public void onClose() {
        System.out.println("disconnected!");
        // 删除用户
        if (this.user != null) {
            users.remove(this.user.getId());
        }
    }

    // 后端从Client接收消息时，会触发这个函数，未来的具体逻辑在这个函数中写
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("receive message!");
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    // 实现从后端向前端发送信息
    public void sendMessage(String message) {
        // 异步通信过程需要加锁
        synchronized (this.session) {
            try {
                this.session.getBasicRemote().sendText(message); // 将信息发送到前端
            } catch (IOException e) {
                e.printStackTrace(); // 为方便调试，直接将异常输出
            }
        }
    }
}