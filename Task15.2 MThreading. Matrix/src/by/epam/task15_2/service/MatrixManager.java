package by.epam.task15_2.service;


import java.util.Random;

public class MatrixManager implements Runnable{
    private int[][] firstMatrix;
    private int[][] secondMatrix;
    private int[][] resultMatrix;
    private String first_thread_name;
    private String second_thread_name;
    private volatile boolean isInitialize;

    public MatrixManager(int matrixlength, String first_thread_name, String second_thread_name){
        firstMatrix = new int[matrixlength][matrixlength];
        secondMatrix = new int[matrixlength][matrixlength];
        resultMatrix = new int[matrixlength][matrixlength];
        this.first_thread_name = first_thread_name;
        this.second_thread_name = second_thread_name;
        isInitialize = false;
    }

    @Override
    public void run(){
        synchronized (this){
            if(!isInitialize){
                initialize(firstMatrix);
                //print(firstMatrix);
                initialize(secondMatrix);
                //print(secondMatrix);
                isInitialize = true;
            }
        }

        if(Thread.currentThread().getName().equals(first_thread_name)){
            multiplyMatrix(0,0);
        }
        if(Thread.currentThread().getName().equals(second_thread_name)){
            multiplyMatrix(1,0);
        }
    }

    public void initialize(int[][] matrix){
        Random random = new Random();
        for(int row=0; row<matrix.length; row++){
            for (int col=0; col<matrix[0].length; col++){
                matrix[row][col] = random.nextInt(20);
            }
        }
    }
    public void printResultMatrix( ){
        for(int row=0; row<resultMatrix.length; row++){
            for(int col = 0; col<resultMatrix[0].length; col++){
                System.out.print(resultMatrix[row][col]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void print(int[][] matrix){
        for(int row=0; row<matrix.length; row++){
            for(int col = 0; col<matrix[0].length; col++){
                System.out.print(matrix[row][col]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void multiplyMatrix(int firstRowIndex, int firstColIndex){
        for(int row = firstRowIndex; row<resultMatrix.length; row = row+2){
            for(int col = firstColIndex; col<resultMatrix[0].length; col++){
                resultMatrix[row][col] = calculate(row,col);
            }
        }
    }

    private int calculate(int row, int col){
        int result = 0;
        for(int i=0; i<firstMatrix.length; i++){
            result += firstMatrix[row][i]*secondMatrix[i][col];
        }
        return result;
    }
}
