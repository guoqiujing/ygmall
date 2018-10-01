package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.OrderAlter;

public interface OrderAlterMapper {

    int deleteByPrimaryKey(String id);

    int insert(OrderAlter record);

    OrderAlter selectByPrimaryKey(String id);

    OrderAlter selectByOrderId(String orderId);

    int updateByPrimaryKey(OrderAlter record);
}