package ru.gd.study.java.andrey.client;

import java.io.IOException;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        ClientConfig clientConfig = new ClientConfig();
        Client client = new Client(clientConfig);
        client.run();
    }
}
