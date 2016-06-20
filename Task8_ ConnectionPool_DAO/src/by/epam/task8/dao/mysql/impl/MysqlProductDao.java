package by.epam.task8.dao.mysql.impl;

import by.epam.task8.dao.ProductDao;
import by.epam.task8.dao.exception.ConnectionPoolException;
import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.dao.mysql.connection.ConnectionPool;
import by.epam.task8.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class MysqlProductDao implements ProductDao{
    private Logger logger = Logger.getLogger(String.valueOf(MysqlOrderDao.class));


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


    public int addProduct(Product product) throws DaoException {
        int count;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(ADD_PRODUCT);

            preparedStatement.setInt(1, product.getCategory_id());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getSupplier_id());
            preparedStatement.setBigDecimal(4, product.getPrice());
            preparedStatement.setBigDecimal(5, product.getQuantity());
            preparedStatement.setBlob(6,product.getImage());
            preparedStatement.setString(7,product.getDescription());
            preparedStatement.setInt(8, product.getDiscount_id());

            count = preparedStatement.executeUpdate();
        }catch (SQLException e){
            //logger.error(e.getMessage());
            throw new DaoException("SQLException" + e);
        } catch (ConnectionPoolException e) {
            //logger.error(e.getMessage());
            throw new DaoException("ConnectionPoolException " + e);
        } finally {
            try{
                preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            }catch (SQLException e){
                //logger.error(e.getMessage());
                throw new DaoException("SQLException" + e);
            }catch (ConnectionPoolException e){
                //logger.error(e.getMessage());
                throw new DaoException("ConnectionPoolException" + e);
            }
        }
        return count;
    }

    public ArrayList<Product> extractProductByCategoryId(int categoryId) throws DaoException {
        ArrayList<Product> productList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_CATEGORY);

            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
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
            //logger.error(e.getMessage());
            throw new DaoException("SQLException " + e);
        } catch (ConnectionPoolException e) {
            //logger.error(e.getMessage());
            throw new DaoException("ConnectionPoolException " + e);
        } finally {
            try{
                preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            }catch (SQLException e){
                //logger.error(e.getMessage());
                throw new DaoException("SQLException" + e);
            }catch (ConnectionPoolException e){
                //logger.error(e.getMessage());
                throw new DaoException("ConnectionPoolException" + e);
            }
        }

        return productList;
    }

}
