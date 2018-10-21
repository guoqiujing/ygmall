package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.dto.OrderDTO;
import cn.myzqu.ygmall.dto.PageDTO;
import cn.myzqu.ygmall.dto.StatisticsDTO;
import cn.myzqu.ygmall.dto.StatisticsForWeekDTO;
import cn.myzqu.ygmall.pojo.CustomerAddress;
import cn.myzqu.ygmall.pojo.Order;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by 的川 on 2018/9/21.
 * 订单服务接口
 */
public interface OrderService {

    /**
     * 创建订单
     * @param address
     * @param orderDTOList
     * @return
     */
    Boolean add(CustomerAddress address , List<OrderDTO> orderDTOList);

    /**
     * 付款
     * @param orderId
     * @param userId
     * @return
     */
    Boolean buy(String orderId,String userId);

    /**
     * 取消订单
     * @param orderId
     * @param userId
     * @return
     */
    Boolean cancel(String orderId,String userId);

    /**
     * 发货
     * @param orderId
     * @param userId
     * @return
     */
    Boolean deliver(String orderId,String userId);

    /**
     * 收货
     * @param orderId
     * @param userId
     * @return
     */
    Boolean receive(String orderId,String userId);

    /**
     * 修改订单状态
     * @param orderId
     * @param userId
     * @param status
     * @return
     */
    Boolean updateStatus(String orderId,String userId,Byte status);

    /**
     * 根据id查询订单详情
     * @param id
     * @return
     */
    Order selectById(String id);

    /**
     * 根据用户id查询订单详情
     * @param customerId
     * @param status
     * @param pageIndex
     * @param pageSize
     * @return
     */
    //PageDTO selectOrderDetailByCustomerId(String customerId, Byte status,Integer pageIndex, Integer pageSize);

    /**
     * 根据用户id和订单状态查询订单
     * @param customerId
     * @param status
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageDTO selectByCustomerId(String customerId, Byte status,Integer pageIndex, Integer pageSize);


    /**
     * 查询一周的销量额（具体到每一天的销售额）
     * @return
     */
    List<StatisticsForWeekDTO>  selectSaleroomForWeek();

    Map<String,String> statisticalOrder();

}
