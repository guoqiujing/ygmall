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

    List<Integer> findAllCategoriesId();

    public List<Categories> findAllL1Categories();

    public List<Categories> findAllL3Categories();

    public List<Categories> getL23CategoriesById(int id);

    public Integer updateCategories(Categories categories);

    public Integer deleteCategories(Integer id);

    public Integer insertCategories(Categories categories);

    String completeCategories(String goodsId);

    String completeCategoriesSpu(String spuId);
}