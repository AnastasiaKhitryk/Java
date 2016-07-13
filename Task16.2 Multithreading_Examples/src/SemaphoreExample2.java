import java.util.concurrent.Semaphore;
/*
Два семафора регулируют потоки исполнения поставщика и потребителя
И гарантируют, что после каждого вызова put() будет следовать get()
 */
public class SemaphoreExample2 {
    public static void main(String[] args) {
        Q q = new Q();
        Thread thread1 = new Thread(new Consumer(q));
        Thread thread2 = new Thread(new Producer(q));
    }
}

class Q{
    int n;

    static Semaphore semCon = new Semaphore(0);
    static Semaphore semProd = new Semaphore(1);

    void get(){
        try{
            semCon.acquire();
        }catch (InterruptedException ex){
            System.out.println("InterruptedException"+ex);
        }
        semProd.release();
    }

    void put(int n){
        try{
            semProd.acquire();
        }catch (InterruptedException ex){
            System.out.println("InterruptedException"+ex);
        }
        this.n=n;
        semCon.release();
    }
}

 class Producer implements Runnable{
     Q q;
     Producer(Q q){
         this.q=q;
     }
     public void run(){
         for(int i=0;i<20;i++){
             q.put(i);
         }
     }
 }

class Consumer implements Runnable{
    Q q;
    Consumer(Q q){
        this.q=q;
    }
    public void run(){
        for(int i=0;i<20;i++){
            q.get();
        }
    }
}











