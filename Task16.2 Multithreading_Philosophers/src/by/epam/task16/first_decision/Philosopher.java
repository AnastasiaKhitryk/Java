package by.epam.task16.first_decision;

import by.epam.task16.first_decision.Fork;
/*
Философы – потоки, которые делают некоторую работу.
Вилки – разделяемые ресурсы, используемые потоками для выполнения работы.
Каждому потоку, чтобы делать работу нужно взять 2 ресурса (вилка справа и слева)

Решение 1: Пронумеровать потоки и ресурсы.
Причем, в порядке против часовой стрелки.

Цикл из следующих этапов:
        1.	возьмем левую вилку
        2.	возьмем правую вилку
        3.	поедим
        4.	положим левую вилку
        5.	положим правую вилку
        6.	подумать
*/
public class Philosopher implements Runnable {

    private volatile boolean isRunning = true;

    private String name;

    private Fork minNumberFork;
    private Fork maxNumberFork;

    public Philosopher(String name, Fork leftFork, Fork rightFork) {
        this.name = name;

        if(leftFork.getNumber() < rightFork.getNumber()){
            this.minNumberFork = leftFork;
            this.maxNumberFork = rightFork;
        }
        else{
            this.minNumberFork = rightFork;
            this.maxNumberFork = leftFork;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        try {
            while (isRunning){

                long thinkingTime = 1000L;
                Thread.sleep(thinkingTime);

                synchronized (minNumberFork){
                    Thread.sleep(100);
                    synchronized (maxNumberFork){
                        long eatingTime = 1000L;
                        Thread.sleep(eatingTime);
                    }
                }
            }
        } catch (InterruptedException e) {
        }
    }

    public void stopRunning(){
        isRunning = false;
    }
}

