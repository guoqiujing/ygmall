package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.OrderAlterMapper;
import cn.myzqu.ygmall.pojo.OrderAlter;
import cn.myzqu.ygmall.service.OrderAlterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 的川 on 2018/10/1.
 */
@Service
public class OrderAlterServiceImpl implements OrderAlterService{

    @Autowired
    private OrderAlterMapper orderAlterMapper;

    @Override
    public OrderAlter selectByOrderId(String orderId) {
        return orderAlterMapper.selectByOrderId(orderId);
    }
}
