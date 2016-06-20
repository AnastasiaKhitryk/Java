package by.training.news.dao;

import by.training.news.dao.exception.DAOException;
import by.training.news.domain.Catalog;

public interface INewsDAO {
    void saveNews(Catalog catalog) throws DAOException;
    Catalog getCatalog() throws DAOException;
}
