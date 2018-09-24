package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.Attribute;

import java.util.List;

public interface AttributeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Attribute record);

    int insertSelective(Attribute record);

    Attribute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attribute record);

    int updateByPrimaryKey(Attribute record);

    List<Attribute> selectByCategoryId(Integer CategoryId);

    int deleteByCategoryId(Integer categoryId);
}