import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
/*
Позволяет потоку исполнения находиться в режиме ожидания, пока не наступит
одно или более событие.
Объект этого класса создается с количеством событий, которые должны произойти до
того, как будет снята блокировка.
Когда событие происходит значение счетчика уменьшается.
Когда значение счетчика равно 0, то самоблокировка снимается.
Для ожидания по самоблокировке - метод await() или await(long ожидание, TimeUnit единица_времени)
 Для извещения о событии  метод countDown()
 */

    public static void main(String[] args) {
        CountDownLatch cd1 = new CountDownLatch(5); //устанавливаем самоблокировку

        Thread thread1 = new Thread(new MyThread(cd1));

        try{
            cd1.await();// исполнение главного потока приостанавливается пока
            //счетчик не станет равен 0
        }catch (InterruptedException ex){
            System.out.println("InterruptedException"+ex);
        }
    }
}

class MyThread implements Runnable{
    CountDownLatch latch;

    MyThread(CountDownLatch latch){
        this.latch=latch;
    }

    public void run(){
        for(int i=0;i<5;i++){
            System.out.println(i);
            latch.countDown(); // уменьшаем счетчик событий на единицу
        }
    }
}
