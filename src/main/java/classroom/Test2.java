package classroom;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket1 = serverSocket.accept();
        InputStream in = socket1.getInputStream();
        Scanner scanner = new Scanner(in);
        System.out.println(scanner.next());
        System.out.flush();
    }
}
