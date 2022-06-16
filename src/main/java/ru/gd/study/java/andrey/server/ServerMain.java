package ru.gd.study.java.andrey.server;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        ServerConfig serverConfig = new ServerConfig();
        Server server = new Server(serverConfig);
        server.run();
    }
}
