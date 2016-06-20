package by.epam.task8.dao.factory;

import by.epam.task8.dao.*;

import java.util.logging.Logger;

public abstract class DaoFactory {

    private static final int MYSQL = 1;
    private static final int XMLFILE = 2;

    private static final DaoFactory mySqlDaoFactory = new MysqlDaoFactory();

    private Logger logger = Logger.getLogger(getClass());

    public abstract CategoryDao getCategoryDAO();
    public abstract CharacteristicDao getCharacteristicDAO();
    public abstract DiscountDao getDiscountDAO();
    public abstract OrderDao getOrderDAO();
    public abstract ProductDao getProductDAO();
    public abstract SupplierDao getSupplierDAO();
    public abstract UserDao getUserDAO();

    public static DaoFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL:
                return mySqlDaoFactory;
            case XMLFILE:
                return null;
            default:
                return null;
        }
    }
}
