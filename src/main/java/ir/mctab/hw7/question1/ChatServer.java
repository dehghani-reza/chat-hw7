package ir.mctab.hw7.question1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        int port = 6070;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            Scanner in = new Scanner(socket.getInputStream());
            Formatter out = new Formatter(socket.getOutputStream());
            Scanner system = new Scanner(System.in);
            String text;
            do {
                text = in.next();
                System.out.println(text);
                out.format(system.next());
                out.flush();
            } while (!text.equals("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
