package by.training.news.DAO;

import by.training.news.DAO.exception.DAOException;
import by.training.news.domain.Catalog;
import by.training.news.domain.News;

import javax.xml.bind.JAXBException;

public interface INewsDAO {
    void saveNews(Catalog catalog) throws DAOException;
    Catalog getCatalog() throws DAOException;
}
