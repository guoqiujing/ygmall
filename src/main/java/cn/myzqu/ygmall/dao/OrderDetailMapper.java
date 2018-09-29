package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.OrderDetail;

import java.util.List;

public interface OrderDetailMapper {

    int deleteById(String id);

    int insert(OrderDetail record);

    OrderDetail selectById(String id);

    List<OrderDetail> selectByOrderId(String orderId);

    int updateById(OrderDetail record);

}