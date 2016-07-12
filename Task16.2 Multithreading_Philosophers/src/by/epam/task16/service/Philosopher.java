package by.epam.task16.service;

import by.epam.task16.entity.Fork;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Philosopher implements Runnable {
    private static final Logger logger = LogManager.getRootLogger();

    private volatile boolean isRunning = true;

    private String name;

    private Fork minNumberFork;
    private Fork maxNumberFork;

    public Philosopher(String name, Fork leftFork, Fork rightFork) {
        this.name = name;
    /*
    В конструктор философа передается 2 ссылки (на вилку слева то него
    и вилку справа). Далее определяется какия из этих вилок имеет наименьший номер
    и соотвественно будет захвачена философом первая.
     */
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
        logger.debug("Philosopher " + name + " started");

        try {
            while (isRunning){
            /*
            Философ проводит определенное время размышляя
             */
                //long thinkingTime = (long) (Math.random() * 9000 + 1000);
            /*
            Одинаковое время размышления для всех философов установлено для
            симуляции ситуации потенциальной взаимной блокировки (все философы
            садятся за стол одновременно).
             */
                long thinkingTime = 1000L;
                logger.debug("Philosopher " + name + " will be thinking next " + thinkingTime + " ms");
                Thread.sleep(thinkingTime);

            /*
            Далее философ берет вилку с меньшим номером, блокируя ее на
            время пользования, либо ожидает пока вилка освободится.
             */
                synchronized (minNumberFork){
                    logger.debug("Philosopher " + name + " took fork number " + minNumberFork.getNumber());
                    Thread.sleep(100);
                /*
                После философ берет вилку с большим номером, так же блокируя
                ее на вермя пользования, либо ожидает ее освобождения.
                 */
                    synchronized (maxNumberFork){
                        logger.debug("Philosopher " + name + " took fork number " + maxNumberFork.getNumber());
                    /*
                    Философ проводит определенное время за едой, пользуясь вилками
                    */
                        //long eatingTime = (long) (Math.random() * 9000 + 1000);
                        long eatingTime = 1000L;
                        logger.debug("Philosopher " + name + " will be eating next " + eatingTime + " ms");
                        Thread.sleep(eatingTime);
                    }
                    logger.debug("Philosopher " + name + " free fork number " + maxNumberFork.getNumber());
                }
                logger.debug("Philosopher " + name + " free fork number " + minNumberFork.getNumber());
            /*
            После приема пищи философ освобождает обе вилки и возвращается
            к размышлениям.
             */
            }
            logger.debug("Philosopher " + name + " finished");
        } catch (InterruptedException e) {
            logger.error("Philosopher " + name + " get into trouble");
        }
    }

    public void stopRunning(){
        isRunning = false;
    }
}

