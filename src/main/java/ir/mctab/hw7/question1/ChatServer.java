package ir.mctab.hw7.question1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//server side
public class ChatServer {
    public static void main(String[] args) throws IOException {
        int port = 8070;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = null;
        try {
            socket = serverSocket.accept();
            System.out.println("you are now online");
        } catch (IOException e) {
            e.printStackTrace();
        }

//**********************************************************
        Socket finalSocket = socket;
        Runnable output = () -> {
            try {
                DataInputStream in = new DataInputStream(new BufferedInputStream(finalSocket.getInputStream()));
                do {
                    System.out.println("Client say: " + in.readUTF());
                    System.out.flush();
                } while (!in.readUTF().equals("exit"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Thread tr = new Thread(output);
        tr.start();
        try {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            Scanner systemIn = new Scanner(System.in);
            {
                String text;
                do {

                    System.out.println("Write youre massage: ");
                    text = systemIn.nextLine();
                    out.writeUTF(text);
                    out.flush();

                } while (!text.equals("exit"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

