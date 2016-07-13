import java.util.concurrent.Semaphore;
/*
Семафор управляет доступом к общему ресурсу с помощью разрешений.
Если значение счетчика семафора больше нуля, то поток получит доступ к ресурсу.
Иначе поток будет заблокирован до тех пор, пока не сможет получить разрешение.
Если потоку доступ к общему ресурсу больше не нужен, то он освобождает разрешение,
в результате этого, значение счетчика семафора увеличиввается на единицу.
Semaphore(int count)
Semaphore(int count, boolean way) по умолчанию, ожидающим потокам предоставляется
 доступ к ресурсу в неопределенном порядке.
 если way=true, то гарантируется превоначальный доступ тем, кто первый запросил ресурс.
 */
public class SemaphoreExample {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1); //Семафор с разрешением на доступ
        // к ресурсу только для одного потока.

        Thread thread1 = new Thread(new IncThread(semaphore));
        Thread thread2 = new Thread(new DecThread(semaphore));
    }
}

//Общий ресурс
class Shared{
    static int count = 0;
}
//поток исполнения, увеличивающий значение счетчика на 1
class IncThread implements Runnable{
    Semaphore semaphore;

    IncThread(Semaphore semaphore){
        this.semaphore = semaphore;
    }

    public void run(){
        try{
            semaphore.acquire();//семафор используется для получения доступа
            // к общему ресурсу. метод acquire дает разрешение
            // на использование семафора

            for(int i=0; i<5;i++){
                Shared.count++;

                Thread.sleep(1000); //в данный момент можно было бы переключить контекст.
                // Однако семафор это не позволяет сделать
            }

        }catch (InterruptedException ex){
            System.out.println("InterruptedException" + ex);
        }

        semaphore.release(); // освободить разрешение
    }
}

//поток исполнения, уменьшающий значение счетчика на 1
class DecThread implements Runnable{
    Semaphore semaphore;

    DecThread(Semaphore semaphore){
        this.semaphore = semaphore;
    }

    public void run(){
        try{
            semaphore.acquire();//семафор используется для получения доступа
            // к общему ресурсу. метод acquire дает разрешение
            // на использование семафора

            for(int i=0; i<5;i++){
                Shared.count--;

                Thread.sleep(1000); //в данный момент можно было бы переключить контекст.
                // Однако семафор это не позволяет сделать
            }

        }catch (InterruptedException ex){
            System.out.println("InterruptedException" + ex);
        }

        semaphore.release(); // освободить разрешение
    }
}