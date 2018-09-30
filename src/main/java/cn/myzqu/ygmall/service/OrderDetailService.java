package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.OrderDetail;

import java.util.List;

/**
 * Created by 的川 on 2018/9/29.
 */
public interface OrderDetailService {

    List<OrderDetail> selectByOrderId(String orderId);
}
