package by.epam.task8.dao;

import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.entity.Characteristic;

import java.util.ArrayList;

public interface CharacteristicDao extends CommonDao<Characteristic>{
    ArrayList<Characteristic> getAllCharacteristic() throws DaoException;
}

