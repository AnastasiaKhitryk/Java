import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        Thread thread1 = new Thread(new LockThread(lock));
        Thread thread2 = new Thread(new LockThread(lock));

    }
}
// общий ресурс
class Shared{
    static int count = 0;
}
//Поток исполнения, инкрементирующий значение счетчика
class LockThread implements Runnable{
    ReentrantLock lock;
    LockThread(ReentrantLock lock){
        this.lock = lock;
    }

    public void run(){
        try{
            //блокируем счетчик
           lock.lock();
            Shared.count++;
        }catch (InterruptedException ex){
            System.out.println("InterruptedException"+ex);
        } finally {
            //снимаем блокировку
            lock.unlock();
        }
    }
}
