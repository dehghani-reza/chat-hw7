package ir.mctab.hw7.q2;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

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
            try {
                dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            do {
                try {
                    text = dataInputStream.readUTF();
                    System.out.println("server said: " + text);
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
            try {
                dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            do {
                try {
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
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 6070;
        String ip = "localhost";
        Socket socket = new Socket(ip, port);
        System.out.println("Connected");
        Thread out = new Thread(new DataOut(socket));
        Thread in = new Thread(new DataInput(socket));

        Thread[] threads = {in, out};
        for (Thread th : threads) {
            th.start();
        }

    }

}
