package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.AfterSaleAlter;

public interface AfterSaleAlterMapper {
    int deleteByPrimaryKey(String id);

    int insert(AfterSaleAlter record);

    int insertSelective(AfterSaleAlter record);

    AfterSaleAlter selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AfterSaleAlter record);

    int updateByPrimaryKey(AfterSaleAlter record);
}