package com.leopie.plugins.websocket;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws",configurator=GetHttpSessionConfigurator.class)
public class ChatAnnotation {

	Session session;
	HttpSession httpSession;
    //static final AtomicInteger connectionIds = new AtomicInteger(0);
    static final Set<ChatAnnotation> connections = new CopyOnWriteArraySet<ChatAnnotation>();

    @OnOpen
    public void start(Session se,EndpointConfig config) {
    	//获取http session
    	this.httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.session = se;
        connections.add(this);
    }

    @OnClose
    public void end() {
        connections.remove(this);
        System.out.println(this.toString() + "客户端关闭连接");
    }

    @OnMessage
    public void incoming(String message) {
        broadcast(message);
    }

    @OnError
    public void onError(Throwable t) throws Throwable {
        //t.printStackTrace();
    	System.out.println("客户端断开连接");
    }


    public static void broadcast(String msg) {
        for (ChatAnnotation client : connections) {
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    // Ignore
                }
            }
        }
    }
}