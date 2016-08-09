package by.epam.task16.first_decision;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Fork fork1 = new Fork(1);
        Fork fork2 = new Fork(2);
        Fork fork3 = new Fork(3);
        Fork fork4 = new Fork(4);
        Fork fork5 = new Fork(5);

        Philosopher philosopher1 = new Philosopher("Philosopher1", fork1, fork2);
        Philosopher philosopher2 = new Philosopher("Philosopher2", fork2, fork3);
        Philosopher philosopher3 = new Philosopher("Philosopher3", fork3, fork4);
        Philosopher philosopher4 = new Philosopher("Philosopher4", fork4, fork5);
        Philosopher philosopher5 = new Philosopher("Philosopher5", fork5, fork1);

        List<Philosopher> philosophers = new ArrayList<Philosopher>();
        philosophers.add(philosopher1);
        philosophers.add(philosopher2);
        philosophers.add(philosopher3);
        philosophers.add(philosopher4);
        philosophers.add(philosopher5);

        try {
            for(Philosopher philosopher:philosophers){
                new Thread(philosopher).start();
            }

            Thread.sleep(10000);

            for(Philosopher philosopher:philosophers){
                philosopher.stopRunning();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
