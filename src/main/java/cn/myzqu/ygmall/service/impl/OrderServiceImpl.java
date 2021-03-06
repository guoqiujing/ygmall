package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.OrderAlterMapper;
import cn.myzqu.ygmall.dao.OrderDetailMapper;
import cn.myzqu.ygmall.dao.OrderMapper;
import cn.myzqu.ygmall.dto.OrderDTO;
import cn.myzqu.ygmall.dto.PageDTO;
import cn.myzqu.ygmall.dto.StatisticsDTO;
import cn.myzqu.ygmall.dto.StatisticsForWeekDTO;
import cn.myzqu.ygmall.enums.OrderStatusEnum;
import cn.myzqu.ygmall.pojo.*;
import cn.myzqu.ygmall.service.OrderDetailService;
import cn.myzqu.ygmall.service.OrderService;
import cn.myzqu.ygmall.task.OrderTask;
import cn.myzqu.ygmall.utils.KeyUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 的川 on 2018/9/21.
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrderAlterMapper orderAlterMapper;

    @Autowired
    private OrderTask orderTask;

    @Override
    public Map<String,String> add(CustomerAddress address , List<OrderDTO> orderDTOList) {
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
        Double goodsTotalMoney = 0.0;
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
            Map<String,String> map = new HashMap<>();
            map.put("orderId",orderId);
            map.put("money",goodsTotalMoney.toString());
            return map;
        }
        return null;
    }

    @Override
    public Boolean buy(String orderId,String userId) {
        Byte status = OrderStatusEnum.DAIFAHUO.getCode().byteValue();
        if(updateStatus(orderId,userId,status)){
            //用户付款成功后，通知发货（1分钟后自动发货，每天早上8点检查是否需要发货)
            orderTask.deliver(orderId);
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


//    @Override
//    public PageDTO selectOrderDetailByCustomerId(String customerId, Byte status, Integer pageIndex, Integer pageSize) {
//        //分页插件
//        Page<Order> page = PageHelper.startPage(pageIndex,pageSize);
//        List<Order> orders = orderMapper.selectOrderDetailByCustomerIdAndStatus(customerId,status);
//        //获取总记录数
//        int total = (int)page.getTotal();
//        System.out.println("总记录数："+total);
//        if(total<=0)
//            return null;
//        return new PageDTO(orders,total,pageSize,pageIndex);
//    }

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

    @Override
    public List<StatisticsForWeekDTO> selectSaleroomForWeek() {
        return orderMapper.selectSaleroomForWeek();
    }

    @Override
    public Map<String,String> statisticalOrder() {
        Map<String,String> result = new HashMap();
        StatisticsDTO statisticsDTO =
                orderMapper.selectStatistics(7,OrderStatusEnum.DONE.getCode().byteValue());
        //最近一周的成交量（订单状态为完成的订单数量）
        result.put("vol",statisticsDTO.getVol().toString());
        //最近一周的销售量（订单状态为完成的订单所购买的商品总数）
        result.put("volume",statisticsDTO.getVolume().toString());
        //最近一周的销售额（订单状态为完成的订单里的商品金额的总数）
        result.put("saleroom",statisticsDTO.getSaleroom().toString());
        StatisticsDTO statisticsDTO1 =
                orderMapper.selectStatistics(7,null);
        //最近一周的订单数
        result.put("thisWeekOrderNum",statisticsDTO1.getVol().toString());
        StatisticsDTO statisticsDTO2 =
                orderMapper.selectStatistics(14,null);
        //上一周的订单数
        Integer lastWeekOrderNum = statisticsDTO2.getVol() - statisticsDTO1.getVol();
        result.put("lastWeekOrderNum",lastWeekOrderNum.toString());
        //计算利润相关
        Map thisWeekMap = orderMapper.selectGoodsStatistics(7);
        Map lastWeekMap = orderMapper.selectGoodsStatistics(14);
        //最近一周总成本
        Double thisWeekCost = ((BigDecimal)thisWeekMap.get("total_cost")).doubleValue();
        //最近一周总销售
        Double thisWeekPrice = ((BigDecimal)thisWeekMap.get("total_price")).doubleValue();
        result.put("thisWeekPrice",thisWeekPrice.toString());
        //最近一周总利润
        Double thisWeekProfit = thisWeekPrice - thisWeekCost;
        result.put("thisWeekProfit",thisWeekProfit.toString());
        //最近一周总利润率
        Double thisWeekProfitRate = 0.0;
        if(thisWeekPrice > 0){
            thisWeekProfitRate = thisWeekProfit / thisWeekPrice * 100;
        }
        result.put("thisWeekProfitRate",thisWeekProfitRate + "%");
        //上一周总成本
        Double lastWeekCost =
                ((BigDecimal)lastWeekMap.get("total_cost")).doubleValue() - thisWeekCost;
        //上一周总销售
        Double lastWeekPrice =
                ((BigDecimal)lastWeekMap.get("total_price")).doubleValue() - thisWeekPrice;
        result.put("lastWeekPrice",lastWeekPrice.toString());
        //上一周总利润
        Double lastWeekProfit = lastWeekPrice - lastWeekCost;
        result.put("lastWeekProfit",lastWeekProfit.toString());
        //上一周总利润率
        Double lastWeekProfitRate = 0.0;
        if(lastWeekPrice>0){
            lastWeekProfitRate = lastWeekProfit / lastWeekPrice * 100;
        }
        result.put("lastWeekProfitRate",lastWeekProfitRate + "%");
        return result;
    }

}
