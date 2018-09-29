package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {


    int deleteById(String id);

    int insert(Order record);

    Order selectById(String id);

    int updateById(Order record);

    List<Order> selectOrderDetailByCustomerId(String customerId);

    List<Order> selectOrderDetailByCustomerIdAndStatus(@Param("customerId") String customerId, @Param("status") Byte status);

    List<Order> selectByCustomerId(@Param("customerId") String customerId, @Param("status") Byte status);
}