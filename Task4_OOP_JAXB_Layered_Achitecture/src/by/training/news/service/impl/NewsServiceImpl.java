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

    public void saveNewNews(String params) throws ServiceException {
        JSONParser parser = new JSONParser();
        News news = new News();
        Catalog catalog;
        String category;
        String subcategory;

        try{
            JSONObject object = (JSONObject) parser.parse(params);

            category = (String) object.get("category");
            subcategory = (String) object.get("subcategory");

            news.setName((String)object.get("name"));
            news.setProvider((String)object.get("provider"));
            news.setDate((String)object.get("date"));
            news.setBody((String)object.get("body"));

        } catch (ParseException ex) {
            throw new ServiceException("ParseException",ex);
        }

        DAOFactory factory = DAOFactory.getInstance();
        INewsDAO newsDAO = factory.getNewsDAO();

        try{
            catalog = newsDAO.getCatalog();
            catalog = updateCatalog(catalog,category,subcategory,news);
            newsDAO.saveNews(catalog);
        }catch (DAOException ex){
            throw new ServiceException("DAOException", ex);
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
            criteria = (String )object.get("name");
            Catalog catalog = newsDAO.getCatalog();
            resultString = find(catalog, criteria);

        } catch (ParseException ex) {
            throw new ServiceException("ParseException",ex);
        } catch (DAOException ex){
            throw new ServiceException("DAOException",ex);
        }

        return resultString;
    }

    private String find(Catalog catalog, String name){

        String resultString = "";

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
