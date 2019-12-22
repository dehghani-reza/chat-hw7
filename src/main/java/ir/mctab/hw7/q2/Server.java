package ir.mctab.hw7.q2;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    //*****************************************************************************
    static class DataInput implements Runnable {
        Socket socket;
        DataInputStream dataInputStream;
        String status;

        public DataInput(Socket socket) {
            this.socket = socket;
            this.status = status;

        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread());
            String text = "";
            try {
                dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            do {
                try {
                    text = dataInputStream.readUTF();
                    System.out.println(Thread.currentThread() + " said: " + text);
                } catch (IOException e) {
                    e.getMessage();
                }

            } while (!text.equals("exit"));

        }

    }


    //*****************************************************************************
    public static void main(String[] args) throws Exception {
        int port = 6070;
        ServerSocket serverSocket = new ServerSocket(port);
        String status = "";
        Map<String, Socket> key = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        Scanner inte = new Scanner(System.in);

        Thread thread = new Thread(new runi(key, serverSocket));
        thread.start();

        String mtn = "";
        while (!mtn.equals("exit")) {
            System.out.println("for client list press 1 | for chat press 2 ");
            int condition = inte.nextInt();
            switch (condition) {
                case 1:
                    System.out.println(key);
                    break;
                case 2:
                    System.out.println("write your destination");
                    mtn = scanner.nextLine();
                    if (!key.containsKey(mtn)) continue;
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(key.get(mtn).getOutputStream()));
                    System.out.println("write your massage: ");
                    String text = scanner.nextLine();
                    out.writeUTF(text);
                    out.flush();
                    break;
            }
        }


    }//end of main

    static class runi implements Runnable {

        Map<String, Socket> key;
        ServerSocket serverSocket;

        public runi(Map<String, Socket> key, ServerSocket serverSocket) {
            this.key = key;
            this.serverSocket = serverSocket;
        }

        @Override
        public void run() {
            while (true) {
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("client connected");
                Thread in = new Thread(new DataInput(socket));
                key.put(in.getName(), socket);
                in.start();
            }
        }
    }
}

//********************************************************************************
