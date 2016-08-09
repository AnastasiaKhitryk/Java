package by.epam.task16.second_decision;

public class Philosopher implements Runnable{
    private final Fork fork1, fork2;
    private Integer totalEaten;

    public Philosopher(Fork f1, Fork f2, Integer totalEaten) {
        fork1 = f1;
        fork2 = f2;
        this.totalEaten = totalEaten;
    }

    @Override
    public void run(){
        while (true) {
            take(fork1);
            take(fork2);
            eat();
            put(fork1);
            put(fork2);
            think();
        }
    }

    private void take(Fork fork){
        try{
            fork.onTake();
        }catch (InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

    private void put(Fork fork){
        try{
            fork.onPut();
        }catch (InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

    private void think() {
        Util.waitMillis(1);
    }

    private void eat() {
        incrementTotalEaten();
        Util.waitMillis(1);
    }

    private synchronized Integer incrementTotalEaten(){
        return totalEaten++;

    }
}
