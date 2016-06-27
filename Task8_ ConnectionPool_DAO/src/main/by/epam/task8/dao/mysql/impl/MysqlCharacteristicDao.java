package by.epam.task8.dao.mysql.impl;


import by.epam.task8.dao.CharacteristicDao;
import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.entity.Characteristic;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlCharacteristicDao extends MysqlCommonActions implements CharacteristicDao {
    private Logger logger = Logger.getLogger(String.valueOf(MysqlCharacteristicDao.class));

    private final static String SELECT_ALL_CHARACTERISTIC = "SELECT * FROM characteristic";

    private final static String ID = "id";
    private final static String NAME = "name";

    @Override
    public List<Characteristic> getAllCharacteristics() throws DaoException {
        List<Characteristic> characteristicList = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = getStatement(connection);

        try {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_CHARACTERISTIC);

            while (resultSet.next()) {
                Characteristic characteristic = new Characteristic();

                characteristic.setId(resultSet.getInt(ID));
                characteristic.setName(resultSet.getString(NAME));

                characteristicList.add(characteristic);
            }

        } catch (SQLException e) {
            logger.error("SQLException " + e);
            throw new DaoException("SQLException" + e);
        } finally {
            releaseData(statement, connection);
        }
        return characteristicList;
    }

    @Override
    public List<Characteristic> getById(int id) throws DaoException {
        //TODO
        return null;
    }
}
