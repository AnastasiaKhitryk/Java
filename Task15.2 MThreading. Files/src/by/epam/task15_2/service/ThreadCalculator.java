package by.epam.task15_2.service;

import by.epam.task15_2.dao.FileManager;
import by.epam.task15_2.dao.exception.DaoException;
import by.epam.task15_2.entity.InputFile;

public class ThreadCalculator implements Runnable{
    private int fileNumber;

    public ThreadCalculator(int fileNumber) {
        this.fileNumber = fileNumber;
    }

    @Override
    public void run() {
        try {
            FileManager fileManager = FileManager.getInstance();
            InputFile inputFile = fileManager.readContentFromFile(fileNumber);

            int action = inputFile.getAction();
            double numberA = inputFile.getNumberA();
            double numberB = inputFile.getNumberB();
            double actionCalculationResult = 0.0;

            switch (action) {
                case 1:
                    actionCalculationResult = actionCalculationResult + numberA + numberB;
                    break;
                case 2:
                    actionCalculationResult = actionCalculationResult + (numberA * numberB);
                    break;
                case 3:
                    actionCalculationResult = actionCalculationResult + (Math.pow(numberA, 2.0) + Math.pow(numberB, 2.0));
                    break;
            }
            CalculatorManager.getInstance().addToCalculationResult(actionCalculationResult);
        } catch (DaoException ex) {
            ex.printStackTrace();
        }
    }
}
