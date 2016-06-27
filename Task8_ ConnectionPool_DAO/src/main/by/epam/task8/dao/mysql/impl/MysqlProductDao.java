package by.epam.task8.dao.mysql.impl;

import by.epam.task8.dao.ProductDao;
import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.entity.Product;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlProductDao extends MysqlCommonActions implements ProductDao {
    private Logger logger = Logger.getLogger(String.valueOf(MysqlProductDao.class));


    private final static String ADD_PRODUCT = "INSERT INTO product (category_id, name, supplier_id, price, quantity, image, description, discount_id) VALUES (?,?,?,?,?,?,?,?)";
    private final static String SELECT_PRODUCT_BY_CATEGORY = "SELECT * FROM product WHERE category_id = ?";

    private final static String ID = "id";
    private final static String CATEGORY_ID = "category_id";
    private final static String NAME = "name";
    private final static String SUPPLIER_ID = "supplier_id";
    private final static String PRICE = "price";
    private final static String QUANTITY = "quantity";
    private final static String IMAGE = "image";
    private final static String DESCRIPTION = "description";
    private final static String DISCOUNT_ID = "discount_id";

    @Override
    public int addProduct(Product product) throws DaoException {
        int count;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = getPreparedStatement(connection, ADD_PRODUCT);

        try {
            preparedStatement.setInt(1, product.getCategory_id());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getSupplier_id());
            preparedStatement.setBigDecimal(4, product.getPrice());
            preparedStatement.setBigDecimal(5, product.getQuantity());
            preparedStatement.setBlob(6, product.getImage());
            preparedStatement.setString(7, product.getDescription());
            preparedStatement.setInt(8, product.getDiscount_id());

            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException" + e);
            throw new DaoException("SQLException" + e);
        } finally {
            releaseData(preparedStatement, connection);
        }
        return count;
    }

    @Override
    public List<Product> getById(int categoryId) throws DaoException {
        List<Product> productList = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = getPreparedStatement(connection, SELECT_PRODUCT_BY_CATEGORY);

        try {
            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();

                product.setId(resultSet.getInt(ID));
                product.setCategory_id(resultSet.getInt(CATEGORY_ID));
                product.setName(resultSet.getString(NAME));
                product.setSupplier_id(resultSet.getInt(SUPPLIER_ID));
                product.setPrice(resultSet.getBigDecimal(PRICE));
                product.setQuantity(resultSet.getBigDecimal(QUANTITY));
                product.setImage(resultSet.getBlob(IMAGE));
                product.setDescription(resultSet.getString(DESCRIPTION));
                product.setDiscount_id(resultSet.getInt(DISCOUNT_ID));

                productList.add(product);
            }

        } catch (SQLException e) {
            logger.error("SQLException" + e);
            throw new DaoException("SQLException " + e);
        } finally {
            releaseData(preparedStatement, connection);
        }

        return productList;
    }

}
