package ir.mctab.hw7.question2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSide1 {

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
                }while (!text.equals("exit"));

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
                }while (!text.equals("exit"));
            }
        }


    //*****************************************************************************
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 6070;
        String ip = "localhost";
        Socket socket = new Socket(ip, port);
        System.out.println("Connected");
        Thread out = new Thread(new DataOut(socket));
        Thread in = new Thread(new DataInput(socket));
        Thread in1 = new Thread(new DataInput(socket));
        Thread[] threads = {in,in1, out};
        for (Thread th : threads) {
            th.start();
        }

    }

}
