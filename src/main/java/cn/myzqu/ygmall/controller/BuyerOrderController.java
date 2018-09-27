package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.dto.OrderDTO;
import cn.myzqu.ygmall.dto.PageDTO;
import cn.myzqu.ygmall.enums.OrderStatusEnum;
import cn.myzqu.ygmall.enums.ResultEnum;
import cn.myzqu.ygmall.exception.CustomException;
import cn.myzqu.ygmall.form.OrderForm;
import cn.myzqu.ygmall.service.OrderService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import cn.myzqu.ygmall.vo.Result;
import cn.myzqu.ygmall.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 的川 on 2018/9/25.
 * 买家端订单控制器
 */
@RestController
@RequestMapping("buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    //创建订单
    @PostMapping("/create")
    public ResultVO create(OrderForm orderForm) {

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }

    //用户获取订单列表
    @GetMapping("/list/{id}")
    public Result list(@PathVariable("id") String id,
                       @RequestParam(value = "status", defaultValue = "0") Integer status,
                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(id)) {
            log.error("【查询订单列表】id为空");
            throw new CustomException(1,"id为空");
        }
        //判断status是否合法
        PageDTO pageDTO = null;
        if(OrderStatusEnum.CANCELED.getCode().equals(status)||
                OrderStatusEnum.DAIFAHUO.getCode().equals(status)||
                OrderStatusEnum.DAISHOUHUO.getCode().equals(status)||
                OrderStatusEnum.DONE.getCode().equals(status)||
                OrderStatusEnum.OBLIGATION.getCode().equals(status)) {
            pageDTO = orderService.selectOrderDetailByCustomerId(id, new Byte(status.toString()), page, size);
        }else{
            //查询所有
            pageDTO = orderService.selectOrderDetailByCustomerId(id,page,size);
        }
        if(pageDTO!=null){
            BootstrapTableVO bootstrapTableVO =  new BootstrapTableVO(pageDTO.getTotal(),pageDTO.getRows());
            return ResultVOUtil.success(bootstrapTableVO);
        }
        return ResultVOUtil.error("请求失败！");
    }
}
