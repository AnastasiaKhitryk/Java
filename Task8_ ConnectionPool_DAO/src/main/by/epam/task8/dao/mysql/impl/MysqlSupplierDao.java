package by.epam.task8.dao.mysql.impl;

import by.epam.task8.dao.SupplierDao;
import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.entity.Supplier;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlSupplierDao extends MysqlCommonActions implements SupplierDao{

    private Logger logger = Logger.getLogger(String.valueOf(MysqlSupplierDao.class));

    private final static String SELECT_ALL_SUPPLIER = "SELECT * FROM supplier";

    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String DESCRIPTION = "description";
    private final static String IMAGE = "image";

    @Override
    public List<Supplier> getAllOrder() throws DaoException {
        List<Supplier> supplierList = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = getStatement(connection);

        try{
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
            logger.error("SQLException " + e);
            throw new DaoException("SQLException " + e);
        } finally {
            releaseData(statement, connection);
        }

        return supplierList;
    }

    @Override
    public List<Supplier> getById(int id) throws DaoException {
        //TODO
        return null;
    }
}
