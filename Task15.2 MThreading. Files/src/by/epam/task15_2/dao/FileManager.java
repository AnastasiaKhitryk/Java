package by.epam.task15_2.dao;

import by.epam.task15_2.dao.exception.DaoException;
import by.epam.task15_2.entity.InputFile;
import by.epam.task15_2.service.CalculatorManager;
import by.epam.task15_2.start.Main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private static final String filePrefix = "in";
    private static final String fileExtension = ".txt";
    private static final char directorySeparator = '/';
    private static final String  rowSymbols = "\r\n";
    private static final char spaceSymbol = ' ';
    private static final String outputFileName = "out.txt";


    private static final FileManager instance = new FileManager();

    private FileManager(){}

    public static FileManager getInstance() {
        return instance;
    }

    public InputFile readContentFromFile(int fileNumber) throws DaoException {
        InputFile inputFile=null;

        try (FileReader fileReader = new FileReader(Main.workingDirectoryPath +
                directorySeparator + filePrefix + fileNumber  + fileExtension)){
            StringBuilder fileContent = new StringBuilder();

            inputFile = new InputFile(fileNumber);

            int symbol = 0;
            while ((symbol = fileReader.read()) != -1){
                fileContent.append((char) symbol);
            }
            String fileContentStr = fileContent.toString();
            String actionIdStr = fileContentStr.substring(0, fileContentStr.indexOf(rowSymbols));
            String numberAStr = fileContentStr.substring(fileContentStr.indexOf(rowSymbols) + 2,
                    fileContentStr.indexOf(spaceSymbol));
            String numberBStr = fileContentStr.substring(fileContentStr.indexOf(spaceSymbol));

            inputFile.setAction(Integer.parseInt(actionIdStr));
            inputFile.setNumberA(Double.parseDouble(numberAStr));
            inputFile.setNumberB(Double.parseDouble(numberBStr));
        } catch (FileNotFoundException ex) {
            throw new DaoException("File not found", ex);
        } catch (IOException ex) {
            throw new DaoException("Reading file exception", ex);
        }
        return inputFile;
    }

    public void createOutputFile(double result) throws DaoException {
        try (FileWriter fileWriter = new FileWriter(Main.workingDirectoryPath+directorySeparator+outputFileName)) {
            fileWriter.write(Double.toString(result));
        } catch (FileNotFoundException ex) {
            throw new DaoException("File not found", ex);
        } catch (IOException ex) {
            throw new DaoException("Create output file exception", ex);
        }
    }
}
