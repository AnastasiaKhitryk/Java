package by.training.news.service;

import by.training.news.service.exception.ServiceException;

public interface IService {
    void saveNewNews(String params) throws ServiceException;
    String findNews(String params) throws ServiceException;
}
