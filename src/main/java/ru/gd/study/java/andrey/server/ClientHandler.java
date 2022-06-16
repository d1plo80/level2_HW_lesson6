package ru.gd.study.java.andrey.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.inputStream = new ObjectInputStream(clientSocket.getInputStream());
        this.outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
    }

    private Object readMessage() throws IOException {
        Object message = inputStream.readUTF();
        System.out.println(message);
        return message;
    }

    public void sendMessage(String message) throws IOException {
        outputStream.writeUTF(message);
        outputStream.flush();
    }

    private void closeResources() {
        try {
            clientSocket.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (!clientSocket.isInputShutdown()) {
                readMessage();
                sendMessage(scanner.nextLine());
            }
        } catch (IOException e) {
            closeResources();
        }
    }
}
