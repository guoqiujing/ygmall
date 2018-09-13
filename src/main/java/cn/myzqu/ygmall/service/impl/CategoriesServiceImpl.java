package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.pojo.Categories;
import cn.myzqu.ygmall.dao.CategoriesMapper;
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
}
