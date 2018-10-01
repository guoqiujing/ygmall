package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.OrderAlter;

import java.util.List;

/**
 * Created by 的川 on 2018/10/1.
 */
public interface OrderAlterService {

    //根据订单id查询订单变动表
    List<OrderAlter> selectByOrderId(String orderId);


}
