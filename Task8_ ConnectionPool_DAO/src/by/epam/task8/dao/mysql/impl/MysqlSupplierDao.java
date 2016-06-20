package by.epam.task8.dao.mysql.impl;

import by.epam.task8.dao.SupplierDao;
import by.epam.task8.dao.exception.ConnectionPoolException;
import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.dao.mysql.connection.ConnectionPool;
import by.epam.task8.entity.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class MysqlSupplierDao implements SupplierDao{

    private Logger logger = Logger.getLogger(String.valueOf(MysqlOrderDao.class));

    private final static String SELECT_ALL_SUPPLIER = "SELECT * FROM supplier";

    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String DESCRIPTION = "description";
    private final static String IMAGE = "image";

    public ArrayList<Supplier> extractAllOrder() throws DaoException {
        ArrayList<Supplier> supplierList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try{
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_SUPPLIER);

            while (resultSet.next()){
                Supplier supplier = new Supplier();

                supplier.setId(resultSet.getInt(ID));
                supplier.setName(resultSet.getString(NAME));
                supplier.setDescription(resultSet.getString(DESCRIPTION));
                supplier.setImage(resultSet.getBlob(IMAGE));

                supplierList.add(supplier);
            }
        } catch (SQLException e) {
            //logger.error(e.getMessage());
            throw new DaoException("SQLException " + e);
        } catch (ConnectionPoolException e) {
            //logger.error(e.getMessage());
            throw new DaoException("ConnectionPoolException " + e);
        } finally {
            try{
                statement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            }catch (SQLException e){
                //logger.error(e.getMessage());
                throw new DaoException("SQLException" + e);
            }catch (ConnectionPoolException e){
                //logger.error(e.getMessage());
                throw new DaoException("ConnectionPoolException" + e);
            }
        }

        return supplierList;
    }

}
