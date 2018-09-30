package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.exception.CustomException;
import cn.myzqu.ygmall.pojo.OrderDetail;
import cn.myzqu.ygmall.service.OrderDetailService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 的川 on 2018/9/29.
 */
@RestController
@RequestMapping("buyer/order/detail")
@Slf4j
public class BuyerOrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    /**
     * 根据订单id获取订单明细
     * @param id
     * @return
     */
    @GetMapping("/list/{id}")
    public Result info(@PathVariable("id") String id){
        if(StringUtils.isEmpty(id)){
            log.error("【查询订单】id为空");
            throw new CustomException(1,"id为空");
        }
        List<OrderDetail> orderDetails = orderDetailService.selectByOrderId(id);
        if(orderDetails!=null){
            return ResultVOUtil.success(orderDetails);
        }
        return ResultVOUtil.error("暂时没有订单");
    }
}
