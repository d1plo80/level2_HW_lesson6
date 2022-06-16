package ru.gd.study.java.andrey.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServerConfig {
    private int port;

    public ServerConfig() {
        portSetup();
    }

    public int getPort() {
        return port;
    }

    private void portSetup() {
        try {
            InputStream resourceAsStream = Server.class.getClassLoader().getResourceAsStream("config.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            String text = properties.getProperty("serverPort");
            port = Integer.parseInt(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
