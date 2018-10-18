package cn.myzqu.ygmall.vo;

import cn.myzqu.ygmall.pojo.Comment;
import cn.myzqu.ygmall.pojo.OrderDetail;

/**
 * Created by CC on 2018/10/18.
 */
public class ComemntOrderDetailVo extends Comment{
    private OrderDetail orderDetail;

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
}
