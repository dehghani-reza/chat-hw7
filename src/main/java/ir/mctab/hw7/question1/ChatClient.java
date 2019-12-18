package ir.mctab.hw7.question1;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Formatter;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {

        String ip="localhost";
        int port=8070;
        Socket socket;

        {
            try {
                socket = new Socket(ip, port);
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
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}