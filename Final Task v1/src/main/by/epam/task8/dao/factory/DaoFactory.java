package by.epam.task8.dao.factory;

import by.epam.task8.dao.*;
import org.apache.log4j.Logger;

public abstract class DaoFactory {

    private Logger logger = Logger.getLogger(getClass());

    private static final DaoFactory mySqlDaoFactory = new MysqlDaoFactory();

    public static final int MYSQL = 1;
    public static final int XMLFILE = 2;

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
