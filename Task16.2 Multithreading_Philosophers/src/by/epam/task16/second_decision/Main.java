package by.epam.task16.second_decision;

public class Main {
    public static void main(String[] args) {
        Infrastructure infrastructure = new Infrastructure(5);
        infrastructure.newPhilosopher(1, 2).start();
        infrastructure.newPhilosopher(2, 3).start();
        infrastructure.newPhilosopher(3, 4).start();
        infrastructure.newPhilosopher(4, 5).start();
        infrastructure.newPhilosopher(5, 1).start();

        showProgress(infrastructure);
    }

    private static void showProgress(Infrastructure infrastructure) {
        long oldValue = -1;
        while (true) {
            int newValue = infrastructure.getTotalEaten();
            if (newValue > oldValue) {
                System.out.println("Total eaten: " + newValue);
                oldValue = newValue;
            }
            Util.waitMillis(1);
        }
    }
}
