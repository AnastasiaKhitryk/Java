import java.util.Random;
/*
Задача на умножение 2-х матриц. Количество потоков неограничено.
Данное решение с использованием многопоточности эффективно при больших размерах матрицы.
В ходе решение было установлено среднее время выполнение для матриц размером 300*300
1) с использованием многопоточности: 231,4ms
2) однопоточная программа: 328,4ms

При размере матриц 3*3 зачения таковы:
1) с использованием многопоточности:8ms
2) однопоточная программа: 1ms
 */
public class Main {
    final static int FIRST_MATRIX_ROWS  = 3;
    final static int FIRST_MATRIX_COLS  = 3;
    final static int SECOND_MATRIX_ROWS = FIRST_MATRIX_COLS;
    final static int SECOND_MATRIX_COLS = 3;

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        final int[][] firstMatrix  = new int[FIRST_MATRIX_ROWS][FIRST_MATRIX_COLS];
        final int[][] secondMatrix = new int[SECOND_MATRIX_ROWS][SECOND_MATRIX_COLS];
        generateRandomValues(firstMatrix); //инициализация 1-ой матрицы
        generateRandomValues(secondMatrix);//инициализация 2-ой матрицы

        //multiplyMatrix(firstMatrix,secondMatrix); //умножение матриц без многопоточности
        multiplyMatrixMT(firstMatrix, secondMatrix, Runtime.getRuntime().availableProcessors());//умножение используя многопоточность

        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("программа выполнялась " + timeSpent + " миллисекунд");

    }

    public static void generateRandomValues(final int[][] matrix){
        final Random random = new Random();

        for (int row = 0; row < matrix.length; ++row){
            for (int col = 0; col < matrix[row].length; ++col){
                matrix[row][col] = random.nextInt(100);
            }
        }
    }

    private static int[][] multiplyMatrixMT(final int[][] firstMatrix,final int[][] secondMatrix,int threadCount){
        final int rowCount = firstMatrix.length;
        final int colCount = secondMatrix[0].length;
        final int[][] result = new int[rowCount][colCount];
        final int cellsForThread = (rowCount * colCount) / threadCount; // количество ячеек на каждый поток
        final MultiplierThread[] multiplierThreads = new MultiplierThread[threadCount]; //массив потоков исполнения

        int firstIndex = 0;

        for (int threadIndex = threadCount - 1; threadIndex >= 0; --threadIndex) {
            //счетчик на создание потоков и корректное распределение ячеек между ними

            int lastIndex = firstIndex + cellsForThread;
            if (threadIndex == 0) { //если это последний поток, то он вычисляет все оставшиеся ячейки из-за остатка от деления
                lastIndex = rowCount * colCount;
            }
            multiplierThreads[threadIndex] = new MultiplierThread(firstMatrix, secondMatrix, result, firstIndex, lastIndex);
            multiplierThreads[threadIndex].start();
            firstIndex = lastIndex;
        }
        try {
            for (final MultiplierThread multiplierThread : multiplierThreads){
                multiplierThread.join();
            }
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException " + e);
        }
        return result;
    }

    private static int[][] multiplyMatrix(final int[][] firstMatrix, final int[][] secondMatrix) {
        final int rowCount = firstMatrix.length;
        final int colCount = secondMatrix[0].length;
        final int sumLength = secondMatrix.length;
        final int[][] result = new int[rowCount][colCount];
        for (int row = 0; row < rowCount; ++row) {
            for (int col = 0; col < colCount; ++col) {
                int sum = 0;
                for (int i = 0; i < sumLength; ++i){
                    sum += firstMatrix[row][i] * secondMatrix[i][col];
                }
                result[row][col] = sum;
            }
        }
        return result;
    }

    public static void printOnConsole(final int[][] matrix){
        for(int row=0; row<matrix.length;row++){
            for (int col=0;col<matrix[row].length;col++){
                System.out.print(matrix[row][col]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
