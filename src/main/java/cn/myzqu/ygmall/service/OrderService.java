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

    Boolean add(CustomerAddress address , List<OrderDTO> orderDTOList);
    Order selectById(String id);
    PageDTO selectOrderDetailByCustomerId(String customerId, Byte status,Integer pageIndex, Integer pageSize);
    PageDTO selectByCustomerId(String customerId, Byte status,Integer pageIndex, Integer pageSize);
}
