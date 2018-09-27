package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.OrderAlter;

public interface OrderAlterMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderAlter record);

    int insertSelective(OrderAlter record);

    OrderAlter selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderAlter record);

    int updateByPrimaryKey(OrderAlter record);
}