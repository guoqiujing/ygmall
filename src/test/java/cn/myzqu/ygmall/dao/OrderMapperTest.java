package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 的川 on 2018/9/27.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext-*.xml")
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void selectOrderDetailByCustomerId() throws Exception {
        String customerId = "f81986cd58214b2b9cc2815ecdd95d3e";
        List<Order> orderList = orderMapper.selectOrderDetailByCustomerId(customerId);
        Iterator it = orderList.iterator();
        while (it.hasNext()){
            System.out.println(it.next().toString());
        }
    }

    @Test
    public void selectOrderDetailByCustomerIdAndStatus() throws Exception {
        String customerId = "f81986cd58214b2b9cc2815ecdd95d3e";
        List<Order> orderList = orderMapper.selectOrderDetailByCustomerIdAndStatus(customerId,null);
        Iterator it = orderList.iterator();
        while (it.hasNext()){
            System.out.println(it.next().toString());
        }
    }

}