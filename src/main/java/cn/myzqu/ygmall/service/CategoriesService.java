package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.Categories;

import java.util.List;

/**
 * Created by Simon on 2018/9/13.
 */
public interface CategoriesService {
    List<Categories> findAllCategories();
}
