import java.util.concurrent.Exchanger;

public class ExchangerExample {
    /*
    класс Exchanger работает так: ожидает до тех пор , пока 2 отдельных потока исполнения не
    вызовут его метод exchange();
    как только это произойдет, он произведет обмен данными.

    В данной программе создаем 2 потока исполнения. В одном создается пустой буфер,
    принимающий данные из другого потока. Таким образом первый поток меняет пустую строку
    на полную.
     */

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();//объект синхронизации
        //обмена символьными строками.

        Thread thread1 = new Thread(new UseString(exchanger));
        Thread thread2 = new Thread(new MakeString(exchanger));
    }
}
//Поток заполняющий символьную строку
class MakeString implements Runnable{
    Exchanger<String> ex;
    String string;

    MakeString(Exchanger<String> ex){
        this.ex = ex;
        string = new String();
    }

    public void run(){
        char ch = 'A';
        for(int i=0;i<3;i++){
            for(int j=0;j<5;j++){
                string+=(char) ch++;
                try{
                    string = ex.exchange(string);//метод синхронизации
                }catch (InterruptedException ex){
                    System.out.println("InterruptedException"+ex);
                }
            }
        }
    }
}
//Поток использующий символьную строку
class UseString implements Runnable{
    Exchanger<String> ex;
    String string;

    UseString(Exchanger<String> ex){
        this.ex = ex;
    }

    public void run(){
        for(int i=0;i<3;i++){
            try{
                string=ex.exchange(new String());//метод синхронизации
            }catch (InterruptedException ex){
                System.out.println("InterruptedException"+ex);
            }
        }
    }
}
