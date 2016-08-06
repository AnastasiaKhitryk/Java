package by.epam.task15_2.start;

import by.epam.task15_2.dao.exception.DaoException;
import by.epam.task15_2.entity.InputFile;
import by.epam.task15_2.service.CalculatorManager;
import by.epam.task15_2.dao.FileCreator;
import by.epam.task15_2.service.exception.ServiceException;

public class Main {
    public static final String workingDirectoryPath = "C://work/";
    public static void main(String[] args){
        try {
            FileCreator fileCreator = FileCreator.getInstance();
            for(int i = 1; i <= 5; i++){
                InputFile inputFile = fileCreator.generateContent(i);
                fileCreator.writeContentToFile(inputFile);
            }

            CalculatorManager calculatorManager = CalculatorManager.getInstance();
            calculatorManager.startCalculation();
        } catch (ServiceException | DaoException ex) {
            ex.printStackTrace();
        }
    }
}
