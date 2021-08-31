import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Job {
    public static ArrayList<String> arrayList= new ArrayList<>();
    public static int arySize ;

    public static void main(String[] args) throws Exception {
        Producer thread1 = new Producer();
        Consumer thread2 = new Consumer();
        thread1.start();
        thread2.start();

    }
}

class Producer extends Thread{
    Reader reader = new FileReader("ProdcerTask/src/T1File.txt");
   BufferedReader bfr = new BufferedReader(reader);

    Producer() throws FileNotFoundException {
    }

    @Override
    public synchronized void run() {
        String line = null ;
        try {
            while( bfr.readLine() != null) {
                line = bfr.readLine();
                System.out.println(line);
                Job.arrayList.add(line);
                notifyAll();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}

class Consumer extends Thread{
    @Override
    public synchronized void run() {

            for (int i =0; i < Job.arySize; i++) {
                System.out.println(Job.arrayList.get(i));
                notify();
                try {
                    wait(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}
