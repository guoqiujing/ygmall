package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.CategoriesMapper;
import cn.myzqu.ygmall.pojo.Categories;
import cn.myzqu.ygmall.service.CategoriesService;
import cn.myzqu.ygmall.vo.CategoriesVO;
import cn.myzqu.ygmall.vo.GoodsCategoriesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Simon on 2018/9/13.
 */
@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesMapper categoriesMapper;

    public CategoriesVO getAllCompleteCategories(){
        CategoriesVO categoriesVO=new CategoriesVO();
        HashMap<Integer,String> name_map=new HashMap<>();
        HashMap<Integer,HashSet> l12_map=new HashMap<>();
        HashMap<Integer,HashSet> l23_map=new HashMap<>();
        List<Integer> categoriesIds=categoriesMapper.findAllCategoriesId();
        List<GoodsCategoriesVO> goodsCategoriesVOList=new ArrayList<>();
        for (Integer tempId:categoriesIds) {
            GoodsCategoriesVO goodsCategoriesVO=getCompleteCategoriesById(tempId);
            if(goodsCategoriesVO!=null){
                goodsCategoriesVOList.add(goodsCategoriesVO);
                name_map.put(goodsCategoriesVO.getId(),goodsCategoriesVO.getName());
                name_map.put(goodsCategoriesVO.getParentId(),goodsCategoriesVO.getParentName());
                name_map.put(goodsCategoriesVO.getGrandId(),goodsCategoriesVO.getGrandName());
                l12_map.put(goodsCategoriesVO.getGrandId(),new HashSet<>());
                l23_map.put(goodsCategoriesVO.getParentId(),new HashSet<>());
            }
        }
        HashSet l12_list=new HashSet<>();
        HashSet l23_list=new HashSet<>();
        for (GoodsCategoriesVO tempGoodsCategoriesVO:goodsCategoriesVOList) {
            l12_list=l12_map.get(tempGoodsCategoriesVO.getGrandId());
            l12_list.add(tempGoodsCategoriesVO.getParentId());
            l12_map.put(tempGoodsCategoriesVO.getGrandId(),l12_list);
            l23_list=l23_map.get(tempGoodsCategoriesVO.getParentId());
            l23_list.add(tempGoodsCategoriesVO.getId());//这句话被执行后，123map的所有key的值就加上了123list

            l23_map.put(tempGoodsCategoriesVO.getParentId(),l23_list);
        }
        categoriesVO.setName_map(name_map);
        categoriesVO.setL12_map(l12_map);
        categoriesVO.setL23_map(l23_map);
        return categoriesVO;
    }


    @Override
    public List<Categories> findAllCategories() {
        List<Categories> categoriesList=categoriesMapper.findAllCategories();
        return categoriesList;
    }


    public GoodsCategoriesVO getCompleteCategoriesById(Integer id){
        GoodsCategoriesVO goodsCategoriesVO=new GoodsCategoriesVO();
        Categories categories=categoriesMapper.selectByPrimaryKey(id);
        if(categories.getGrandLevel()!=null&&categories.getParentLevel()!=null){
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
            return goodsCategoriesVO;
        }
        return null;
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
