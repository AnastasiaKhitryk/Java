package by.epam.task16.second_decision;

import java.util.ArrayList;
import java.util.List;

public class Infrastructure {
    List<Fork> forks = new ArrayList<>();
    Integer totalEaten = new Integer(0);
    int NUMBER;

    public Infrastructure(int n) {
        NUMBER = n;
        for (int i = 0; i < n; i++) {
            forks.add(new Fork());
        }
    }

    public Thread newPhilosopher(int fork1, int fork2) {
        Philosopher p = new Philosopher(forks.get(fork1 - 1), forks.get(fork2 - 1),totalEaten);
        return new Thread(p, "Philosoper " + fork1);
    }

    public int getTotalEaten() {
        return totalEaten;
    }
}
