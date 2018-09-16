package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.Brand;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    Brand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

    List<Brand> selectBrand(Map<String, Object> map);

    int deleteByIdList(List<Integer> idList);
}