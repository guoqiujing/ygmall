package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.Order;

import java.util.List;

public interface OrderMapper {


    int deleteById(String id);

    int insert(Order record);

    Order selectById(String id);

    int updateById(Order record);

    List<Order> selectOrderDetailByCustomerId(String customerId);

    List<Order> selectOrderDetailByCustomerIdAndStatus(String customerId,Byte status);

}