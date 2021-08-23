import java.io.*;
import java.util.Scanner;

public class Job {
    public static int dayNumber;
    public static void main(String[] args) throws Exception {
    Producer thread1 = new Producer();
    Consumer thread2 = new Consumer();
        thread1.start();
        thread2.start();


    }

}
class Producer extends Thread{
    FileInputStream fileInputStream = new FileInputStream("/home/rojo/IdeaProjects/ProdcerTask/src/T1File.txt");
    Scanner scanLine = new Scanner(fileInputStream);

    Producer() throws FileNotFoundException {

    }

    @Override
    public synchronized void run() {
        while (scanLine.hasNextLine()) {
            int no = scanLine.nextInt();
            Job.dayNumber = no;
            no = 0;
            System.out.println("Produced day: "+ Job.dayNumber);
            try {
                notify();
                wait(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
class Consumer extends Thread{
    FileOutputStream fileOutputStream = new FileOutputStream("/home/rojo/IdeaProjects/ProdcerTask/src/T2File.txt");

    Consumer() throws FileNotFoundException {
    }



    @Override
    public synchronized void run() {
        Boolean isTrue =true;
        while (isTrue) {
            if (Job.dayNumber != 0) {
                try {
                    fileOutputStream.write(Job.dayNumber);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Consumed day: "+Job.dayNumber);
                Job.dayNumber = 0;
                try {
                    notify();
                    wait(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                //isTrue = false;
            }

        }
    }
}
