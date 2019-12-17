package ir.mctab.hw7.question1;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Formatter;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {

        String ip="localhost";
        int port=6070;
        Socket socket;

        {
            try {
                socket = new Socket(ip, port);
                Scanner socketIn = new Scanner(socket.getInputStream());
                Formatter socketOutput = new Formatter(socket.getOutputStream());
                Scanner systemIn = new Scanner(System.in);
                {
                    String text;
                    text = systemIn.next();
                    while (!text.equals("exit")) {
                        socketOutput.format(text);
                        socketOutput.flush();
                        String received = socketIn.next();
                        System.out.println(received);
                    }
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}