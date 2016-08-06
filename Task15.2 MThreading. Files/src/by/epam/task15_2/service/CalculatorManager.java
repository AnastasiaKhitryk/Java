package by.epam.task15_2.service;

import by.epam.task15_2.dao.FileManager;
import by.epam.task15_2.dao.exception.DaoException;
import by.epam.task15_2.service.exception.ServiceException;

import java.util.ArrayList;

public class CalculatorManager {
    private static final CalculatorManager instance = new CalculatorManager();
    private static double calculationResult;

    private CalculatorManager(){}

    public static CalculatorManager getInstance() {
        return instance;
    }

    public void startCalculation() throws ServiceException {
        ArrayList<Thread> actionCalculatorThreads = new ArrayList<>(5);
        for(int i = 1; i <= 5; i++){
            Thread actionCalculatorThread = new Thread(new ThreadCalculator(i));
        }

        for(Thread thread : actionCalculatorThreads){
            thread.start();
        }

        for(Thread thread: actionCalculatorThreads){
            try {
                thread.join();
            } catch (InterruptedException ex) {
                throw new ServiceException("Calculation exception", ex);
            }
        }

        try {
            FileManager fileManager = FileManager.getInstance();
            fileManager.createOutputFile(getCalculationResult());
        } catch (DaoException e) {
            throw new ServiceException("Calculation exception", e);
        }
    }

    public synchronized double getCalculationResult() {
        return calculationResult;
    }

    public synchronized void setCalculationResult(double calculationResult) {
        this.calculationResult = calculationResult;
    }

    public synchronized void addToCalculationResult(double addingNumber) {
        this.calculationResult = calculationResult + addingNumber;
    }
}
