package by.training.news.dao;


import by.training.news.dao.impl.NewsDAOImpl;

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
