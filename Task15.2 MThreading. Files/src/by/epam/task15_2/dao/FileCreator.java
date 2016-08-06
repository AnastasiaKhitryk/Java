package by.epam.task15_2.dao;

import by.epam.task15_2.dao.exception.DaoException;
import by.epam.task15_2.entity.InputFile;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {
    private static final FileCreator instance = new FileCreator();

    private static final char rowSymbol1 = '\r';
    private static final char rowSymbol2 = '\n';
    private static final char spaceSymbol = ' ';
    private static final String filePrefix = "in";
    private static final String fileExtension = ".txt";
    private static final char directorySeparator = '/';
    private static final String workingDirectoryURI="C://work/";

    private FileCreator(){}

    public static FileCreator getInstance() {
        return instance;
    }

    public InputFile generateContent(int fileNumber) {
        InputFile inputFile = new InputFile(fileNumber);

        inputFile.setAction(generateAction());
        inputFile.setNumberA(generateNumber());
        inputFile.setNumberB(generateNumber());
        inputFile.setFilePath(generateFilePath(fileNumber));
        return inputFile;
    }

    public void writeContentToFile(InputFile inputFile) throws DaoException{
        try (FileWriter fileWriter = new FileWriter(inputFile.getFilePath())) {
            fileWriter.write(Integer.toString(inputFile.getAction()));
            fileWriter.append(rowSymbol1);
            fileWriter.append(rowSymbol2);
            fileWriter.append(Double.toString(inputFile.getNumberA()));
            fileWriter.append(spaceSymbol);
            fileWriter.append(Double.toString(inputFile.getNumberB()));
        } catch (FileNotFoundException ex) {
            throw new DaoException("File not Found", ex);
        } catch (IOException ex) {
            throw new DaoException("Input Exception", ex);
        }
    }

    private int generateAction() {
        return (int) Math.ceil(Math.random() * 3);
    }

    private double generateNumber() {
        return Math.random();
    }

    private String generateFilePath(int fileNumber){
        return workingDirectoryURI + directorySeparator + filePrefix + fileNumber + fileExtension;
    }
}
