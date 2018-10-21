package cn.myzqu.ygmall.dto;

import lombok.Data;

/**
 * Created by 的川 on 2018/10/21.
 */
@Data
public class StatisticsDTO {

    //成交量（订单状态为完成的订单数量）
    private Integer vol;
    //销售量（订单状态为完成的订单所购买的商品总数）
    private Integer  volume;
    //销售额（订单状态为完成的订单里的商品金额的总数）
    private Double saleroom;

//    select count(o.id) as vol, sum(order_detail.count) as volume, sum(o.real_total_money) as saleroom
//
//    from `order` o , order_detail
//
//    where o.`status` = 3
//
//    and o.id = order_detail.order_id
//
//    and DATEDIFF(NOW(),o.create_time) < 7

}
