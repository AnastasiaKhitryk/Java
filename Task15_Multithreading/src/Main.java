import java.util.Random;

public class Main {
    final static int FIRST_MATRIX_ROWS  = 3;
    final static int FIRST_MATRIX_COLS  = 3;
    final static int SECOND_MATRIX_ROWS = FIRST_MATRIX_COLS;
    final static int SECOND_MATRIX_COLS = 3;


    public static void main(String[] args){
        final int[][] firstMatrix  = new int[FIRST_MATRIX_ROWS][FIRST_MATRIX_COLS];
        final int[][] secondMatrix = new int[SECOND_MATRIX_ROWS][SECOND_MATRIX_COLS];


        generateRandomValues(firstMatrix);
        generateRandomValues(secondMatrix);

        printOnConsole(firstMatrix);
        printOnConsole(secondMatrix);
        printOnConsole(multiplyMatrix(firstMatrix,secondMatrix));
        printOnConsole(multiplyMatrixMT(firstMatrix, secondMatrix, Runtime.getRuntime().availableProcessors()));

    }

    public static void generateRandomValues(final int[][] matrix){
        final Random random = new Random();

        for (int row = 0; row < matrix.length; ++row){
            for (int col = 0; col < matrix[row].length; ++col) {
                matrix[row][col] = random.nextInt(100);
            }
        }
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

    private static int[][] multiplyMatrixMT(final int[][] firstMatrix,final int[][] secondMatrix,int threadCount){
        final int rowCount = firstMatrix.length;
        final int colCount = secondMatrix[0].length;
        final int[][] result = new int[rowCount][colCount];

        final int cellsForThread = (rowCount * colCount) / threadCount;
        int firstIndex = 0;
        final MultiplierThread[] multiplierThreads = new MultiplierThread[threadCount];

        for (int threadIndex = threadCount - 1; threadIndex >= 0; --threadIndex) {
            int lastIndex = firstIndex + cellsForThread;
            if (threadIndex == 0) {
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
            ;
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
                for (int i = 0; i < sumLength; ++i)
                    sum += firstMatrix[row][i] * secondMatrix[i][col];
                result[row][col] = sum;
            }
        }
        return result;
    }
}
