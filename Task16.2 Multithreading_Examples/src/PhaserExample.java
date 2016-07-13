import java.util.concurrent.Phaser;

public class PhaserExample {
    /*
    синхронизация потоков, которые представляют одну или несколько
    стадий выполнения действия.
    Данный класс можно использовать для синхронизации только однойфазы

     */

    public static void main(String[] args) {
        Phaser phaser = new Phaser(1); //1 соответствует основному потоку исполнения
        int currentPhase;

        Thread thread1 = new Thread(new MyThread(phaser));
        Thread thread2 = new Thread(new MyThread(phaser));
        Thread thread3 = new Thread(new MyThread(phaser));

        //ожидание всеми потоками завершения первой фазы
        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();

        //ожидание всеми потоками завершения второй фазы
        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();


        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();

        //снять основной поток исполненния с регистрации
        phaser.arriveAndDeregister();

        if(phaser.isTerminated()){
            System.out.println("Синхронизатор фаз завершен");
        }
    }
}

class MyThread implements Runnable{
    Phaser phaser;

    MyThread(Phaser phaser){
        this.phaser = phaser;
        phaser.register();
    }

    public void run(){
        phaser.arriveAndAwaitAdvance(); //известить о достижении фазы
        phaser.arriveAndAwaitAdvance(); //известить о достижении 2ой фазы
        phaser.arriveAndDeregister(); //известить о достижении 2ой фазы
    }
}
