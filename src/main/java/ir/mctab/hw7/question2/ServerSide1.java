package ir.mctab.hw7.question2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSide1 {

    //*****************************************************************************
    static class DataInput implements Runnable {
        Socket socket;
        DataInputStream dataInputStream;

        public DataInput(Socket socket) {
            this.socket = socket;

        }

        @Override
        public void run() {
            String text = "";
            do {
                try {
                    dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    text = dataInputStream.readUTF();
                    System.out.println("said: " + text);
                } catch (IOException e) {
                    e.getMessage();
                }
            } while (!text.equals("exit"));

        }

    }

    //*****************************************************************************
    static class DataOut implements Runnable {
        Socket socket;
        DataOutputStream dataOutputStream;
        Scanner scanner = new Scanner(System.in);

        public DataOut(Socket socket) {
            this.socket = socket;

        }

        @Override
        public void run() {
            String text = "";
            do {
                try {
                    dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    text = scanner.nextLine();
                    dataOutputStream.writeUTF(text);
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (!text.equals("exit"));
        }

    }

    //*****************************************************************************
    public static void main(String[] args) throws Exception {
        int port = 6070;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        System.out.println("Connection is ok");
        Thread out = new Thread(new DataOut(socket));
        Thread in = new Thread(new DataInput(socket));
        Thread[] threads = {in, out};
        for (Thread th : threads) {
            th.start();
        }

    }

}
