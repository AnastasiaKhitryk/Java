import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
  /*
   Определяет объект синхронизации, который приостанавливается до тех пор,
   пока определенное кол-во потоков исполнения не достигнет некоторой барьерной точки

   */

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3,new BarAction());
        Thread thread1 = new Thread(new MyThread(cb));
        Thread thread2 = new Thread(new MyThread(cb));
        Thread thread3 = new Thread(new MyThread(cb));
    }
}

//объект этого класса вызывается при достижении барьера типа CyclicBarrier
class BarAction implements Runnable{
    public void run(){
        System.out.println("Барьер достигнут");
    }
}
// поток исполнения, использующий барьер типа CyclicBarrier
class MyThread implements Runnable{
    CyclicBarrier cyclicBarrier;

    MyThread(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier=cyclicBarrier;
    }

    public void run(){
        try{
            cyclicBarrier.await();
        }catch (BrokenBarrierException ex){
            System.out.println("BrokenBarrierException"+ex);
        }catch (InterruptedException ex){
            System.out.println("InterruptedException"+ex);
        }
    }
}
