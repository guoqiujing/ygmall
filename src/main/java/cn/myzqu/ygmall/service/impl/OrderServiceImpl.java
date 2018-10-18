package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.OrderDetailMapper;
import cn.myzqu.ygmall.dao.OrderMapper;
import cn.myzqu.ygmall.dto.PageDTO;
import cn.myzqu.ygmall.pojo.Brand;
import cn.myzqu.ygmall.pojo.Order;
import cn.myzqu.ygmall.pojo.OrderDetail;
import cn.myzqu.ygmall.service.OrderDetailService;
import cn.myzqu.ygmall.service.OrderService;
import cn.myzqu.ygmall.utils.KeyUtil;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 的川 on 2018/9/21.
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public Boolean add() {
        //获取收货地址

        //获取总订单信息
        //生成订单表id
        String orderId = KeyUtil.genUniqueKey();
        Order order = new Order();

        order.setId(orderId);
        OrderDetail orderDetail = new OrderDetail();

        if(orderMapper.insert(order)>0){
            //添加订单明细信息\

        }


        return null;
    }

    @Override
    public Order selectById(String id) {
        return orderMapper.selectById(id);
    }


    @Override
    public PageDTO selectOrderDetailByCustomerId(String customerId, Byte status, Integer pageIndex, Integer pageSize) {
        //分页插件
        Page<Order> page = PageHelper.startPage(pageIndex,pageSize);
        List<Order> orders = orderMapper.selectOrderDetailByCustomerIdAndStatus(customerId,status);
        //获取总记录数
        int total = (int)page.getTotal();
        System.out.println("总记录数："+total);
        if(total<=0)
            return null;
        return new PageDTO(orders,total,pageSize,pageIndex);
    }

    @Override
    public PageDTO selectByCustomerId(String customerId, Byte status, Integer pageIndex, Integer pageSize) {
        //分页插件
        Page<Order> page = PageHelper.startPage(pageIndex,pageSize);
        List<Order> orders = orderMapper.selectByCustomerId(customerId,status);
        //获取总记录数
        int total = (int)page.getTotal();
        System.out.println("总记录数："+total);
        if(total<=0)
            return null;
        return new PageDTO(orders,total,pageSize,pageIndex);
    }
}
