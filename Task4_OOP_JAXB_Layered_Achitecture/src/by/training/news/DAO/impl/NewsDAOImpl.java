package by.training.news.DAO.impl;

import by.training.news.DAO.INewsDAO;
import by.training.news.DAO.exception.DAOException;
import by.training.news.DAO.parser.impl.JaxbParser;
import by.training.news.domain.Catalog;
import by.training.news.domain.Category;
import by.training.news.domain.News;
import by.training.news.domain.SubCategory;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class NewsDAOImpl implements INewsDAO {

    File file = new File("src/by/training/news/source/catalog.xml");
    JaxbParser parser = new JaxbParser();


    public void saveNews(Catalog catalog) throws DAOException {
        try {
            parser.saveObject(file, catalog);
        }
        catch (JAXBException ex){
            throw new DAOException("JAXBException", ex);
        }
    }


    public Catalog getCatalog() throws DAOException {
        Catalog catalog;

        try {
            catalog = (Catalog) parser.getObject(file, Catalog.class);
        }
        catch (JAXBException ex){
            throw new DAOException("JAXBException",ex);
        }
        return catalog;
    }
}
