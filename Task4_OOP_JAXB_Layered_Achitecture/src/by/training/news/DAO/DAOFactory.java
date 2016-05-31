package by.training.news.DAO;

import by.training.news.DAO.impl.NewsDAOImpl;

public class DAOFactory {

    private static DAOFactory instance = null;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {

        if (instance == null)
        {
            instance =  new DAOFactory();
        }

        return instance;
    }

    private INewsDAO newsDAO = new NewsDAOImpl();

    public INewsDAO getNewsDAO(){
        return newsDAO;
    }
}
