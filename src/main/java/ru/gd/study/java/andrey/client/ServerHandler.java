package ru.gd.study.java.andrey.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ServerHandler implements Runnable{
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public ServerHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
    }

    private void sendMessage(String message) throws IOException {
        outputStream.writeUTF(message);
        outputStream.flush();
    }

    private void initMessageListener() {
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println(inputStream.readUTF());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        })
                .start();
    }

    private void closeResources() throws IOException {
        outputStream.close();
        inputStream.close();
        socket.close();
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            initMessageListener();
            boolean b = true;
            while (b) {
                sendMessage(scanner.nextLine());
            }
            closeResources();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
