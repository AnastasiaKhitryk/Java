package by.epam.task16.second_decision;


public class Fork {
    public synchronized void onTake() throws InterruptedException {
        Thread.sleep(100);
    }
    public synchronized void onPut() throws InterruptedException {
        Thread.sleep(100);
    }
}
