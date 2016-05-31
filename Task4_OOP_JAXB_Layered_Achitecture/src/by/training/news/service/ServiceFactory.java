package by.training.news.service;

import by.training.news.service.impl.NewsServiceImpl;

public class ServiceFactory {

    private static ServiceFactory instance = null;

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        if (instance == null) {instance =  new ServiceFactory();}

        return instance;
    }

    private IService service = new NewsServiceImpl();

    public IService getService(){
        return service;
    }
}
