package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.pojo.OrderDetail;
import cn.myzqu.ygmall.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 的川 on 2018/9/29.
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService{


    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public List<OrderDetail> selectByOrderId(String orderId) {
        List<OrderDetail> orderDetails = orderDetailService.selectByOrderId(orderId);
        if(orderDetails.size()>0){
            return orderDetails;
        }
        return null;
    }
}
