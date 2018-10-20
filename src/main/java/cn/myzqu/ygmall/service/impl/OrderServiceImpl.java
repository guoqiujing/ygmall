package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.controller.CustomerAddressController;
import cn.myzqu.ygmall.dao.OrderAlterMapper;
import cn.myzqu.ygmall.dao.OrderDetailMapper;
import cn.myzqu.ygmall.dao.OrderMapper;
import cn.myzqu.ygmall.dto.OrderDTO;
import cn.myzqu.ygmall.dto.PageDTO;
import cn.myzqu.ygmall.enums.OrderStatusEnum;
import cn.myzqu.ygmall.pojo.*;
import cn.myzqu.ygmall.service.OrderDetailService;
import cn.myzqu.ygmall.service.OrderService;
import cn.myzqu.ygmall.utils.KeyUtil;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Autowired
    private OrderAlterMapper orderAlterMapper;

    @Override
    public Boolean add(CustomerAddress address , List<OrderDTO> orderDTOList) {
        //获取总订单信息
        //生成订单表id
        String orderId = KeyUtil.genUniqueKey();
        Order order = new Order();
        BeanUtils.copyProperties(address,order);
        //获取当前操作用户
        String userId = order.getUserId();
        order.setId(orderId);
        List<OrderDetail> orderDetails = new ArrayList<>();
        //插入订单详情表，并计算总金额
        double goodsTotalMoney = 0;
        for(int i = 0;i<orderDTOList.size();i++){
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(orderDTOList.get(i),orderDetail);
            goodsTotalMoney += orderDetail.getCount() * orderDetail.getPrice().doubleValue();
            orderDetail.setId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetail.setUserId(userId);
            System.out.println("orderDetail:"+orderDetail);
            orderDetails.add(orderDetail);
        }
        //订单状态:支付功能还没做，暂时先直接待收货
        Byte status = OrderStatusEnum.OBLIGATION.getCode().byteValue();
        order.setStatus(status);
        order.setCarriage(new BigDecimal(0));
        order.setGoodsTotalMoney(new BigDecimal(goodsTotalMoney));
        order.setRealTotalMoney(new BigDecimal(goodsTotalMoney));
        //System.out.println("order:"+order);
        if(orderMapper.insert(order)>0){
            //添加订单明细信息
            for(int i=0;i<orderDetails.size();i++){
                OrderDetail orderDetail = orderDetails.get(i);
                orderDetail.setStatus(status);
                orderDetailMapper.insert(orderDetail);
                //扣库存
            }
            //记录订单信息变更
            OrderAlter orderAlter= new OrderAlter();
            orderAlter.setId(KeyUtil.genUniqueKey());
            orderAlter.setEntryId(orderId);
            orderAlter.setOperator(userId);
            orderAlter.setState(status);
            orderAlterMapper.insert(orderAlter);
            return true;
        }
        return false;
    }

    @Override
    public Boolean buy(String orderId,String userId) {
        Byte status = OrderStatusEnum.DAIFAHUO.getCode().byteValue();
        if(updateStatus(orderId,userId,status)){
            //用户付款成功后，通知发货（1分钟后自动发货，每天早上8点检查是否需要发货)
            return true;
        }
        return false;
    }

    @Override
    public Boolean cancel(String orderId, String userId) {
        Byte status = OrderStatusEnum.CANCELED.getCode().byteValue();
        if(updateStatus(orderId,userId,status)){
            return true;
        }
        return false;
    }

    @Override
    public Boolean deliver(String orderId, String userId) {
        Byte status = OrderStatusEnum.DAISHOUHUO.getCode().byteValue();
        if(updateStatus(orderId,userId,status)){
            return true;
        }
        return false;
    }

    @Override
    public Boolean receive(String orderId, String userId) {
        Byte status = OrderStatusEnum.DONE.getCode().byteValue();
        if(updateStatus(orderId,userId,status)){
            //客户确定收货后，如果用户一直不确认收货，15天内自动完成订单
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateStatus(String orderId, String userId, Byte status) {
        //修改订单总表状态
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(status);
        if(orderMapper.updateById(order)>0){
            //修改订单详情表
            //根据订单总表id
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setStatus(status);
            orderDetailMapper.updateStatusByOrderId(orderDetail);
        }
        //增加订单变更记录
        OrderAlter orderAlter= new OrderAlter();
        orderAlter.setId(KeyUtil.genUniqueKey());
        orderAlter.setEntryId(orderId);
        orderAlter.setOperator(userId);
        orderAlter.setState(status);
        orderAlterMapper.insert(orderAlter);
        return true;
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
