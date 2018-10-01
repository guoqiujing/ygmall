package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.OrderAlter;

/**
 * Created by 的川 on 2018/10/1.
 */
public interface OrderAlterService {

    //根据订单id查询订单变动表
    OrderAlter selectByOrderId(String orderId);


}
