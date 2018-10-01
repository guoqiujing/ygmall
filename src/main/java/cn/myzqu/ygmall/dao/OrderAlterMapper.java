package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.OrderAlter;

import java.util.List;

public interface OrderAlterMapper {

    int deleteByPrimaryKey(String id);

    int insert(OrderAlter record);

    OrderAlter selectByPrimaryKey(String id);

    List<OrderAlter> selectByOrderId(String orderId);

    int updateByPrimaryKey(OrderAlter record);
}