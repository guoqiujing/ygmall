package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.CategoriesMapper;
import cn.myzqu.ygmall.pojo.Categories;
import cn.myzqu.ygmall.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Simon on 2018/9/13.
 */
@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesMapper categoriesMapper;
    @Override
    public List<Categories> findAllCategories() {
        List<Categories> categoriesList=categoriesMapper.findAllCategories();
        return categoriesList;
    }

    @Override
    public List<Categories> findAllL1Categories() {
        List<Categories> categoriesList=categoriesMapper.findAllL1Categories();
        return categoriesList;
    }

    @Override
    public List<Categories> getL23CategoriesById(int id) {
        List<Categories> categoriesList=categoriesMapper.getL23CategoriesById(id);
        return categoriesList;
    }

    @Override
    public Integer updateCategories(Categories categories) {
        Integer result=categoriesMapper.updateCategories(categories);
        return result;
    }

    @Override
    public Integer deleteCategories(Integer id) {
        Integer result=categoriesMapper.deleteCategories(id);
        return result;
    }

    @Override
    public Integer insertCategories(Categories categories) {
        Integer result=categoriesMapper.insertCategories(categories);
        return result;
    }
}
