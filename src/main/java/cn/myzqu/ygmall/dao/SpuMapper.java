package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.Spu;

import java.util.List;
import java.util.Map;

public interface SpuMapper {
    int deleteByPrimaryKey(String id);

    int insert(Spu record);

    int insertSelective(Spu record);

    Spu selectByPrimaryKey(String id);

    List<Spu> selectIdAndName(Map<String, Object> map);

    int updateByPrimaryKeySelective(Spu record);

    int updateByPrimaryKey(Spu record);
}