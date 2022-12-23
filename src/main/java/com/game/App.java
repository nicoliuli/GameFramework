package com.game;


import com.game.core.config.ServerConfig;
import com.game.core.Node;

public class App {
    public static void main( String[] args ) {
        Node node = new Node(ServerConfig.NODE_ID);
        node.start();
    }
}
