package by.epam.task8.dao.factory;

import by.epam.task8.dao.*;
import by.epam.task8.dao.mysql.impl.*;

public class MysqlDaoFactory extends DaoFactory {

    private final CategoryDao mysqlCategoryDao = new MysqlCategoryDao();
    private final CharacteristicDao mysqlCharacteristicDao = new MysqlCharacteristicDao();
    private final DiscountDao mysqlDiscountDao = new MysqlDiscountDao();
    private final OrderDao mysqlOrderDao = new MysqlOrderDao();
    private final ProductDao mysqlProductDao = new MysqlProductDao();
    private final SupplierDao mysqlSupplierDao = new MysqlSupplierDao();
    private final UserDao mysqlUserDao = new MysqlUserDao();


    @Override
    public CategoryDao getCategoryDAO() {
        return mysqlCategoryDao;
    }

    @Override
    public CharacteristicDao getCharacteristicDAO() {
        return mysqlCharacteristicDao;
    }

    @Override
    public DiscountDao getDiscountDAO() {
        return mysqlDiscountDao;
    }

    @Override
    public OrderDao getOrderDAO() {
        return mysqlOrderDao;
    }

    @Override
    public ProductDao getProductDAO() {
        return mysqlProductDao;
    }

    @Override
    public SupplierDao getSupplierDAO() {
        return mysqlSupplierDao;
    }

    @Override
    public UserDao getUserDAO() {
        return mysqlUserDao;
    }
}
