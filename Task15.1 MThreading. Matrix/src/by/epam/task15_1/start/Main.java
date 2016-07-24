package by.epam.task15_1.start;

import by.epam.task15_1.service.MatrixHelper;

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
    final static int FIRST_MATRIX_ROWS  = 300;
    final static int FIRST_MATRIX_COLS  = 300;
    final static int SECOND_MATRIX_ROWS = FIRST_MATRIX_COLS;
    final static int SECOND_MATRIX_COLS = 300;

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        final int[][] firstMatrix  = new int[FIRST_MATRIX_ROWS][FIRST_MATRIX_COLS];
        final int[][] secondMatrix = new int[SECOND_MATRIX_ROWS][SECOND_MATRIX_COLS];
        MatrixHelper.generateRandomValues(firstMatrix);
        MatrixHelper.generateRandomValues(secondMatrix);

        //MatrixHelper.multiplyMatrix(firstMatrix,secondMatrix); //умножение матриц без многопоточности
        MatrixHelper.multiplyMatrixMT(firstMatrix, secondMatrix, 2);//умножение используя многопоточность

        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("программа выполнялась " + timeSpent + " миллисекунд");
    }
}
