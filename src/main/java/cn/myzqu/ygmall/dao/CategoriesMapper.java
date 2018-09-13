package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.Categories;

import java.util.List;

public interface CategoriesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Categories record);

    int insertSelective(Categories record);

    Categories selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Categories record);

    int updateByPrimaryKey(Categories record);

    List<Categories> findAllCategories();
}