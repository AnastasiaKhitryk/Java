package by.epam.task8.dao;

import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.entity.Characteristic;

import java.util.ArrayList;
import java.util.List;

public interface CharacteristicDao extends CommonDao<Characteristic>{
    List<Characteristic> getAllCharacteristics() throws DaoException;
}

