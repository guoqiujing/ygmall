package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.CategoriesMapper;
import cn.myzqu.ygmall.pojo.Categories;
import cn.myzqu.ygmall.service.CategoriesService;
import cn.myzqu.ygmall.vo.GoodsCategoriesVO;
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

    public GoodsCategoriesVO getCompleteCategoriesById(Integer id){
        GoodsCategoriesVO goodsCategoriesVO=new GoodsCategoriesVO();
        Categories categories=categoriesMapper.selectByPrimaryKey(id);
        if(categories.getGrandLevel()!=null){
            goodsCategoriesVO.setId(categories.getId());
            goodsCategoriesVO.setName(categories.getName());
            Integer parentId=categories.getParentLevel();
            Integer grandId=categories.getGrandLevel();
            String parentName=categoriesMapper.selectByPrimaryKey(parentId).getName();
            String grandName=categoriesMapper.selectByPrimaryKey(grandId).getName();
            goodsCategoriesVO.setParentId(parentId);
            goodsCategoriesVO.setParentName(parentName);
            goodsCategoriesVO.setGrandId(grandId);
            goodsCategoriesVO.setGrandName(grandName);
        }
        return goodsCategoriesVO;
    }


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
