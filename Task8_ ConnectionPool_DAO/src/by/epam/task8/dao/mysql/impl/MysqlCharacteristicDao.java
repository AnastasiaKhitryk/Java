package by.epam.task8.dao.mysql.impl;


import by.epam.task8.dao.CharacteristicDao;
import by.epam.task8.dao.exception.ConnectionPoolException;
import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.dao.mysql.connection.ConnectionPool;
import by.epam.task8.entity.Characteristic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

public class MysqlCharacteristicDao implements CharacteristicDao{
    private Logger logger = Logger.getLogger(String.valueOf(MysqlOrderDao.class));

    private final static String SELECT_ALL_CHARACTERISTIC = "SELECT * FROM characteristic";

    private final static String ID = "id";
    private final static String NAME = "name";

    public ArrayList<Characteristic> extractCharacteristics() throws DaoException {
        ArrayList<Characteristic> characteristicList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try{
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_CHARACTERISTIC);

            while (resultSet.next()){
                Characteristic characteristic = new Characteristic();

                characteristic.setId(resultSet.getInt(ID));
                characteristic.setName(resultSet.getString(NAME));

                characteristicList.add(characteristic);
            }

        }catch (SQLException e){
            //logger.error(e.getMessage());
            throw new DaoException("SQLException" + e);
        } catch (ConnectionPoolException e) {
            //logger.error(e.getMessage());
            throw new DaoException("ConnectionPoolException" + e);
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
        return characteristicList;
    }

}
