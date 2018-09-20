package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.Spu;

public interface SpuMapper {
    int deleteByPrimaryKey(String id);

    int insert(Spu record);

    int insertSelective(Spu record);

    Spu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Spu record);

    int updateByPrimaryKey(Spu record);
}