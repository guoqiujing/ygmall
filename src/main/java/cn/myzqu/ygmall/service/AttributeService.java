package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.Attribute;
import cn.myzqu.ygmall.vo.CategoriesAttributeVO;

import java.util.List;

/**
 * Created by Simon on 2018/9/21.
 */
public interface AttributeService {
    public CategoriesAttributeVO getCategoriesAttributeByCategoryId(Integer categoryId);
    public List<CategoriesAttributeVO> getAllCategoriesAttribute();
    public Integer deleteByCategoryId(Integer categoryId);
    public Integer insertAttribute(Attribute attribute);
}
