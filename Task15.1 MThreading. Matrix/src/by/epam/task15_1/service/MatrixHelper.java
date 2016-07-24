package by.epam.task15_1.service;

import java.util.Random;

public class MatrixHelper {
    public static void generateRandomValues(final int[][] matrix){
        final Random random = new Random();

        for (int row = 0; row < matrix.length; ++row){
            for (int col = 0; col < matrix[row].length; ++col){
                matrix[row][col] = random.nextInt(100);
            }
        }
    }

    public static int[][] multiplyMatrixMT(final int[][] firstMatrix,final int[][] secondMatrix,int threadCount){
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

    public static int[][] multiplyMatrix(final int[][] firstMatrix, final int[][] secondMatrix) {
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
