package com.game;


import com.game.core.config.ServerConfig;
import com.game.core.Node;
import com.game.ws.WS;

public class App {
    public static void main( String[] args ) {
        Node node = new Node(ServerConfig.NODE_ID);
        node.start();

        // 放在单独的线程里启动
        WS ws = new WS();
        ws.startup();
    }
}
