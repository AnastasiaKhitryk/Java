package task15;

public class Main {
    private static final int MATRIX_LENGTH = 3;
    private static final String FIRST_THREAD_NAME = "1";
    private static final String SECOND_THREAD_NAME = "2";

    public static void main(String[] args) throws InterruptedException {
        MatrixManager matrixManager = new MatrixManager(MATRIX_LENGTH,FIRST_THREAD_NAME,SECOND_THREAD_NAME);

        Thread thread1 = new Thread(matrixManager);
        Thread thread2 = new Thread(matrixManager);

        thread1.setName(FIRST_THREAD_NAME);
        thread2.setName(SECOND_THREAD_NAME);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        matrixManager.printResultMatrix();
    }
}
