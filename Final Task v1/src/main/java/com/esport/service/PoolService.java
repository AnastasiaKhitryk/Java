package main.java.com.esport.service;

import main.java.com.esport.service.exception.ServiceException;

public interface PoolService {
    void init() throws ServiceException;
    void destroy() throws ServiceException;
}
