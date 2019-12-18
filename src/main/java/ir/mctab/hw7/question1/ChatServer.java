package ir.mctab.hw7.question1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
//server side
public class ChatServer {
    public static void main(String[] args) throws IOException {
        int port = 8070;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            DataInputStream in =  new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream out =  new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            Scanner systemIn = new Scanner(System.in);
            {
                String text;
                do  {
                    System.out.println("Write youre massage: ");
                    text = systemIn.nextLine();
                    out.writeUTF(text);
                    out.flush();
                    System.out.println(in.readUTF());
                    System.out.flush();
                }while (!text.equals("exit"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

