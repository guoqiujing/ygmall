package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.dto.OrderDTO;
import cn.myzqu.ygmall.dto.PageDTO;
import cn.myzqu.ygmall.pojo.CustomerAddress;
import cn.myzqu.ygmall.pojo.Order;
import com.github.pagehelper.Page;

import java.util.List;

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
    Order selectById(String id);
    PageDTO selectOrderDetailByCustomerId(String customerId, Byte status,Integer pageIndex, Integer pageSize);
    PageDTO selectByCustomerId(String customerId, Byte status,Integer pageIndex, Integer pageSize);


}
