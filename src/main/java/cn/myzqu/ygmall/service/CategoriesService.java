package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.Categories;

import java.util.List;

/**
 * Created by Simon on 2018/9/13.
 */
public interface CategoriesService {
    List<Categories> findAllCategories();
    public List<Categories> findAllL1Categories();
    public List<Categories> getL23CategoriesById(int id);
    public Integer updateCategories(Categories categories);
    public Integer deleteCategories(Integer id);
    public Integer insertCategories(Categories categories);
}
