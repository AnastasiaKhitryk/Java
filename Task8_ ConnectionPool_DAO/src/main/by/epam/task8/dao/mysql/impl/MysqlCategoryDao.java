package by.epam.task8.dao.mysql.impl;

import by.epam.task8.dao.CategoryDao;
import by.epam.task8.dao.exception.ConnectionPoolException;
import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.dao.mysql.connection.ConnectionPool;
import by.epam.task8.entity.Category;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;


public class MysqlCategoryDao implements CategoryDao{
    private Logger logger = Logger.getLogger(String.valueOf(MysqlCategoryDao.class));

    private final static String SELECT_ALL_CATEGORY = "SELECT * FROM category where parent_id = null";

    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String DESCRIPTION = "description";
    private final static String IMAGE = "image";
    private final static String PARENT_ID = "parent_id";

    @Override
    public ArrayList<Category> getAllCategory() throws DaoException{
        ArrayList<Category> categoryList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try{
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_CATEGORY);

            while (resultSet.next()){
                Category category = new Category();

                category.setId(resultSet.getInt(ID));
                category.setName(resultSet.getString(NAME));
                category.setDescription(resultSet.getString(DESCRIPTION));
                category.setImage(resultSet.getBlob(IMAGE));
                category.setParent_id(resultSet.getInt(PARENT_ID));

                categoryList.add(category);
            }

        }catch (SQLException e){
            logger.error("SQLException " + e);
            throw new DaoException("SQLException" + e);
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPoolException " + e);
            throw new DaoException("ConnectionPoolException" + e);
        } finally {
            try{
                statement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            }catch (SQLException e){
                logger.error("SQLException " + e);
                throw new DaoException("SQLException" + e);
            }catch (ConnectionPoolException e){
                logger.error("ConnectionPoolException " + e);
                throw new DaoException("ConnectionPoolException" + e);
            }
        }
        return categoryList;
    }


    @Override
    public ArrayList<Category> getById(int id) throws DaoException {
        //TODO
        return null;
    }
}
