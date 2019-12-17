package classroom;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Hello");
//        FileReader fileReader = new FileReader("C:\\Users\\Reza\\Desktop\\machine_learning.txt");
//        int chcode;
//        while(-1!=(chcode=fileReader.read())){
//            System.out.println((char)chcode);
//        }
//        fileReader.close();


//        File file;
//        FileWriter fileWriter = new FileWriter("C:\\Users\\Reza\\Desktop\\machine.txt", false);
//        for (int i =0 ; i<100 ; i++){
//            fileWriter.write("\thaji\n");
//        }
//        fileWriter.close();

//        List<Byte> list = new ArrayList<>();
//        File file;
//        FileInputStream inputStream = new FileInputStream("C:\\Users\\Reza\\Desktop\\Handbook-Flood-2012.pdf");
//        int bcode;
//        while (-1!= (bcode = inputStream.read())){
//            list.add((byte) bcode);
//        }
//        for (byte i:list) {
//            System.out.println(i);
//
//        }
//
//        byte [] number = {1 , 2 , 33};
//        File file;
//        FileOutputStream stream = new FileOutputStream("C:\\Users\\Reza\\Desktop\\oopo.txt");
//        stream.write(number);
//        stream.close();

//        File f = new File("C:\\Users\\Reza\\Desktop");
//        long le = f.length();
//        boolean b = f.isDirectory();
//        System.out.println(f.lastModified());
//        System.out.println(le);
//        System.out.println(b);
//        System.out.println(f.canRead());
//
//        RandomAccessFile raf = new RandomAccessFile("C:/Users/Reza/Desktop/machine.txt", "rw");
//        System.out.println(raf.readLine());
//        raf.seek(500);
//        raf.writeBytes("salam mamad");

//        try(Scanner scanner = new Scanner(new File("C:/Users/Reza/Desktop/machine_learning.txt"));
//        Formatter formatter = new Formatter("C:/Users/Reza/Desktop/machine.txt");
//        ) {
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                formatter.format("%s\r\n", line);
//            }
//        }

//        ServerSocket serverSocket = new ServerSocket(8888);
//        Socket socket1 = serverSocket.accept();
//        InputStream in = socket1.getInputStream();
//        Scanner scanner = new Scanner(in);
//        System.out.println(scanner.next());
//        System.out.flush();
//
//        Socket socket = new Socket("172.17.34.18",7070);
//        OutputStream out = socket.getOutputStream();
//        Formatter formatter = new Formatter(out);
//        formatter.format("salam!\n");
//        formatter.flush();


//        FileOutputStream f1 = new FileOutputStream("C:/Users/Reza/Desktop/machine.txt");
//        ObjectOutputStream o1 = new ObjectOutputStream(f1);
//        Student st = new Student("Ali", 18.2);
//        o1.writeObject(st);
//        o1.close();
//        System.out.println(st.getAverage());

//        File file;
//        FileInputStream f2 = new FileInputStream("C:/Users/Reza/Desktop/machine.txt");
//        ObjectInputStream o2 = new ObjectInputStream(f2);
//        Student s2 = (Student) o2.readObject();
//        System.out.println(s2.getName());
//        System.out.println(s2.getAverage());
//        o2.close();

        Path path = Paths.get("C:/Users/Reza/Desktop/machine.txt");
        if(!Files.exists(path)) return;


    }// end of method main
}
class Student implements Serializable{
    private String name;
    private double [] grades;
    transient double average;
    public Student(String name , double average){
        this.average=average;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public double getAverage(){
        return average;
    }

    public double[] getGrades() {
        return grades;
    }

    public void setGrades(double[] grades) {
        this.grades = grades;
    }
}
