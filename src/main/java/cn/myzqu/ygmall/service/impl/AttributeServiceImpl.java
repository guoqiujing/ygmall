package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.AttributeMapper;
import cn.myzqu.ygmall.dao.CategoriesMapper;
import cn.myzqu.ygmall.pojo.Attribute;
import cn.myzqu.ygmall.pojo.Categories;
import cn.myzqu.ygmall.service.AttributeService;
import cn.myzqu.ygmall.vo.CategoriesAttributeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 2018/9/21.
 */
@Service
public class AttributeServiceImpl implements AttributeService {
    @Autowired
    private AttributeMapper attributeMapper;
    @Autowired
    private CategoriesMapper categoriesMapper;

    public CategoriesAttributeVO getCategoriesAttributeByCategoryId(Integer categoryId){
        CategoriesAttributeVO categoriesAttributeVO=new CategoriesAttributeVO();
        categoriesAttributeVO.setCategoryId(categoryId);
        //根据三级分类id，查询到最多三个规格名，并保存到封装类中
        List<Attribute> attributes= attributeMapper.selectByCategoryId(categoryId);
        for(int i=0;i<attributes.size();i++){
            categoriesAttributeVO.setAttribute0(attributes.get(0).getName());
            if(i==1)
                categoriesAttributeVO.setAttribute1(attributes.get(1).getName());
            if(i==2)
                categoriesAttributeVO.setAttribute2(attributes.get(2).getName());
        }
        //三次查询类别，并拼接成完整类别名称（类别路径）
        String categoriesName="";
        Categories categories=categoriesMapper.selectByPrimaryKey(categoryId);
        if(categories.getGrandLevel()!=null){
            Integer parentId=categories.getParentLevel();
            Integer grandId=categories.getGrandLevel();
            String parentName=categoriesMapper.selectByPrimaryKey(parentId).getName();
            String grandName=categoriesMapper.selectByPrimaryKey(grandId).getName();
            categoriesName+=grandName+" > "+parentName+" > "+categories.getName();
            categoriesAttributeVO.setCategoriesName(categoriesName);
        }
        return categoriesAttributeVO;
    }

    public List<CategoriesAttributeVO> getAllCategoriesAttribute(){
        List<CategoriesAttributeVO> categoriesAttributeVOList=new ArrayList<>();
        List<Categories> categoriesList=categoriesMapper.findAllL3Categories();
        for (Categories categories:categoriesList) {
            CategoriesAttributeVO categoriesAttributeVO=getCategoriesAttributeByCategoryId(categories.getId());
            if(categoriesAttributeVO!=null)
                categoriesAttributeVOList.add(categoriesAttributeVO);
        }
        return categoriesAttributeVOList;
    }

    public Integer deleteByCategoryId(Integer categoryId){
        List<Attribute> attributeList=attributeMapper.selectByCategoryId(categoryId);
        if(attributeList.size()==0)
            return 1;
        else{
            Integer result=attributeMapper.deleteByCategoryId(categoryId);
            return result;
        }
    }

    public Integer insertAttribute(Attribute attribute){
        Integer result=attributeMapper.insert(attribute);
        System.out.println("impl");
        System.out.println(attribute.getCategoryId());
        System.out.println(attribute.getName());
        return result;
    }

}
