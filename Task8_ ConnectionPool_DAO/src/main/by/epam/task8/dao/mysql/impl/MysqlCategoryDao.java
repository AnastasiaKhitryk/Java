package by.epam.task8.dao.mysql.impl;

import by.epam.task8.dao.CategoryDao;
import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.dao.mysql.connection.ConnectionPool;
import by.epam.task8.entity.Category;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MysqlCategoryDao extends MysqlCommonActions implements CategoryDao {
    private Logger logger = Logger.getLogger(String.valueOf(MysqlCategoryDao.class));

    private final static String SELECT_ALL_CATEGORY = "SELECT * FROM category where parent_id = null";

    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String DESCRIPTION = "description";
    private final static String IMAGE = "image";
    private final static String PARENT_ID = "parent_id";

    @Override
    public List<Category> getAllCategory() throws DaoException {
        List<Category> categoryList = new ArrayList<>();

        Connection connection = getConnection();
        Statement statement = getStatement(connection);

        try {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_CATEGORY);

            while (resultSet.next()) {
                Category category = new Category();

                category.setId(resultSet.getInt(ID));
                category.setName(resultSet.getString(NAME));
                category.setDescription(resultSet.getString(DESCRIPTION));
                category.setImage(resultSet.getBlob(IMAGE));
                category.setParent_id(resultSet.getInt(PARENT_ID));

                categoryList.add(category);
            }

        } catch (SQLException e) {
            logger.error("SQLException " + e);
            throw new DaoException("SQLException" + e);
        } finally {
            releaseData(statement, connection);
        }
        return categoryList;
    }

    @Override
    public List<Category> getById(int id) throws DaoException {
        //TODO
        return null;
    }
}
