package by.training.news.service.impl;

import by.training.news.DAO.DAOFactory;
import by.training.news.DAO.INewsDAO;
import by.training.news.DAO.exception.DAOException;
import by.training.news.domain.*;
import by.training.news.service.IService;
import by.training.news.service.exception.ServiceException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;

public class NewsServiceImpl implements IService{
    
    private static final String CATEGORY = "category";
    private static final String SUB_CATEGORY = "subcategory";
    private static final String NAME = "name";
    private static final String PROVIDER ="provider";
    private static final String DATE = "date";
    private static final String BODY = "body";
    
    private static final String PARSE_EXCEPTION = "ParseException";
    private static final String DAO_EXCEPTION = "DAOException";
    private static final String EMPTY = " "; 

    public void saveNewNews(String params) throws ServiceException {
        JSONParser parser = new JSONParser();
        News news = new News();
        Catalog catalog;
        String category;
        String subcategory;

        try{
            JSONObject object = (JSONObject) parser.parse(params);

            category = (String) object.get(CATEGORY);
            subcategory = (String) object.get(SUB_CATEGORY);

            news.setName((String)object.get(NAME));
            news.setProvider((String)object.get(PROVIDER));
            news.setDate((String)object.get(DATE));
            news.setBody((String)object.get(BODY));

        } catch (ParseException ex) {
            throw new ServiceException(PARSE_EXCEPTION,ex);
        }

        DAOFactory factory = DAOFactory.getInstance();
        INewsDAO newsDAO = factory.getNewsDAO();

        try{
            catalog = newsDAO.getCatalog();
            catalog = updateCatalog(catalog,category,subcategory,news);
            newsDAO.saveNews(catalog);
        }catch (DAOException ex){
            throw new ServiceException(DAO_EXCEPTION, ex);
        }

    }
// + при добавлении новости проверять существует ли такая же

    private Catalog updateCatalog(Catalog catalog, String categoryName, String subCategoryName, News news){

        List<Category> categoryList = catalog.getCategoryList();
        String category = null;
        String subcategory = null;

        for (Category c : categoryList) {
            if (c.getName().equals(categoryName)) {
                category = categoryName;
                List<SubCategory> subCategoryList = c.getSubCategoryList();
                for (SubCategory s : subCategoryList) {
                    if (s.getName().equals(subCategoryName)) {
                        subcategory = subCategoryName;
                        s.addNews(news);
                    }
                }
            }
        }

        if(category == null){
            Category newCategory = new Category();
            SubCategory newSubcategory = new SubCategory();

            newSubcategory.addNews(news);
            newSubcategory.setName(subCategoryName);

            newCategory.addSubCategory(newSubcategory);
            newCategory.setName(categoryName);

            catalog.addCategory(newCategory);

        } else{
            if(subcategory == null){
                for (Category c : categoryList) {
                    if (c.getName().equals(categoryName)) {

                        SubCategory newSubcategory = new SubCategory();

                        newSubcategory.addNews(news);
                        newSubcategory.setName(subCategoryName);

                        c.addSubCategory(newSubcategory);
                    }
                }

            }
        }
        
        return catalog;
    }

    public String findNews(String params)throws ServiceException{
        DAOFactory factory = DAOFactory.getInstance();
        INewsDAO newsDAO = factory.getNewsDAO();
        String criteria;
        String resultString;

        JSONParser parser = new JSONParser();

        try{
            JSONObject object = (JSONObject) parser.parse(params);
            criteria = (String )object.get(NAME);
            Catalog catalog = newsDAO.getCatalog();
            resultString = find(catalog, criteria);

        } catch (ParseException ex) {
            throw new ServiceException(PARSE_EXCEPTION,ex);
        } catch (DAOException ex){
            throw new ServiceException(DAO_EXCEPTION,ex);
        }

        return resultString;
    }

    private String find(Catalog catalog, String name){

        String resultString = EMPTY;

        for(Category c: catalog.getCategoryList()){
            for(SubCategory s:c.getSubCategoryList()){
                for(News n: s.getNewsList()){
                    if(n.getName().equals(name)){
                        resultString += "\n\n"+"Category: "+c.getName()+"; Subcategory: "+s.getName()+"\n"+"Name: "+n.getName()+"\nProvider:"
                                +n.getProvider()+"\nDate: "+n.getDate()+"\nBody: "+n.getBody()+"\n";
                    }
                }
            }

        }

        if(resultString.isEmpty()){
            resultString = "News not found";
        }

        return resultString;
    }

}
