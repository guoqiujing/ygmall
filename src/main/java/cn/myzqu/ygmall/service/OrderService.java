package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.dto.PageDTO;
import cn.myzqu.ygmall.pojo.Order;
import com.github.pagehelper.Page;

/**
 * Created by 的川 on 2018/9/21.
 * 订单服务接口
 */
public interface OrderService {

    Boolean add();

    PageDTO selectOrderDetailByCustomerId(String customerId, Integer pageIndex, Integer pageSize);
    PageDTO selectOrderDetailByCustomerId(String customerId, Byte status,Integer pageIndex, Integer pageSize);

}
