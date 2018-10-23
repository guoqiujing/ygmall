package cn.myzqu.ygmall.controller.order;

import cn.myzqu.ygmall.alipay.config.AlipayConfig;
import cn.myzqu.ygmall.dto.OrderDTO;
import cn.myzqu.ygmall.dto.PageDTO;
import cn.myzqu.ygmall.dto.UserSessionDTO;
import cn.myzqu.ygmall.enums.OrderStatusEnum;
import cn.myzqu.ygmall.exception.CustomException;
import cn.myzqu.ygmall.form.OrderForm;
import cn.myzqu.ygmall.pojo.CustomerAddress;
import cn.myzqu.ygmall.pojo.Order;
import cn.myzqu.ygmall.pojo.OrderAlter;
import cn.myzqu.ygmall.service.CustomerAddressService;
import cn.myzqu.ygmall.service.OrderAlterService;
import cn.myzqu.ygmall.service.OrderService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.Result;
import cn.myzqu.ygmall.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 的川 on 2018/9/25.
 * 买家端订单控制器
 */
@Controller
@RequestMapping("buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderAlterService orderAlterService;

    @Autowired
    private CustomerAddressService customerAddressService;

    //创建订单
    @PostMapping("/create")
    public ModelAndView create(String cart,String address) {
        System.out.println("cart:"+cart);
        System.out.println("address:"+address);
        //获取购物车Json，将购物车Json转为实体类
        CustomerAddress addressPojo = JSONObject.parseObject(address,CustomerAddress.class);
        //将cart 转换为实体类
        List<OrderDTO> orderDTOS = JSONObject.parseArray(cart,OrderDTO.class);
        //添加订单
        Map<String,String> map = orderService.add(addressPojo,orderDTOS);
        if(!map.isEmpty()){
            //跳转到付款页面
            String url = "/page/user/index.jsp?"+"WIDout_trade_no="+map.get("orderId")+
                    "&WIDtotal_amount="+map.get("money");
            ModelAndView mav = new ModelAndView("redirect:"+url);
            return mav;
        }
        ModelAndView mav = new ModelAndView("redirect:/page/user/user.html");
        return mav;
    }


    /**
     * 模拟付款
     * @param orderId
     * @param request
     * @return
     */
    @PostMapping("/buy")
    @ResponseBody
    public Result buy(String orderId ,HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserSessionDTO userSessionDTO = (UserSessionDTO) session.getAttribute("user");
        System.out.println(userSessionDTO);
        if(userSessionDTO==null){
            return ResultVOUtil.error("请先登录");
        }
        if(orderService.buy(orderId,userSessionDTO.getId())){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("付款发生异常");
    }


    @PostMapping("/buy1")
    public ModelAndView buy1(String orderId ,HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserSessionDTO userSessionDTO = (UserSessionDTO) session.getAttribute("user");
        System.out.println(orderId);
        if(userSessionDTO==null){
            //session为空，请先登录
            ModelAndView mav = new ModelAndView("redirect:/page/user/login.html");
            return mav;
        }
        if(orderService.buy(orderId,userSessionDTO.getId())){
            ModelAndView mav = new ModelAndView("redirect:/page/user/user.html");
            return mav;
        }
        ModelAndView mav = new ModelAndView("redirect:/page/user/user.html");
        return mav;
    }


    /**
     * 取消订单
     * @param orderId
     * @param request
     * @return
     */
    @PostMapping("/cancel")
    @ResponseBody
    public Result cancel(String orderId ,HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserSessionDTO userSessionDTO = (UserSessionDTO) session.getAttribute("user");
        System.out.println(userSessionDTO);
        if(userSessionDTO==null){
            return ResultVOUtil.error("请先登录");
        }
        if(orderService.cancel(orderId,userSessionDTO.getId())){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("取消订单发生异常");
    }

    /**
     * 用户确认收货
     * @param orderId
     * @param request
     * @return
     */
    @PostMapping("/receive")
    @ResponseBody
    public Result receive(String orderId ,HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserSessionDTO userSessionDTO = (UserSessionDTO) session.getAttribute("user");
        System.out.println(userSessionDTO);
        if(userSessionDTO==null){
            return ResultVOUtil.error("请先登录");
        }
        if(orderService.receive(orderId,userSessionDTO.getId())){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("确定收货发生异常");
    }

    /**
     * 获取下单信息
     * @param request
     * @return
     */
    @GetMapping("/from")
    @ResponseBody
    public Result getOrderFrom(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return ResultVOUtil.success(session.getAttribute("orderSession"));
    }

    /**
     * 接收商品信息或者购物车信息并跳转到订单页面
     * @param cart
     * @param request
     * @return
     */
    @PostMapping("/to/order")
    public ModelAndView create(String  cart,HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserSessionDTO userSessionDTO = (UserSessionDTO) session.getAttribute("user");
        System.out.println(userSessionDTO);
        if(userSessionDTO==null){
            //session为空，请先登录
            ModelAndView mav = new ModelAndView("redirect:/page/user/login.html");
            return mav;
        }
        //将cart 转换为实体类
        List<OrderDTO> list = JSONObject.parseArray(cart,OrderDTO.class);
        Iterator it = list.iterator();
        //计算总价
        double totalPrice = 0 ;
        while (it.hasNext()){
            OrderDTO item = (OrderDTO)it.next();
            //计算总价格
            double sum = item.getCount() * item.getPrice().doubleValue();
            item.setSum(sum);
            totalPrice += sum;
        }
        //进行操作
        ModelAndView mav = new ModelAndView("redirect:/page/user/order/create.html");
        //将信息暂时存储在session
        session.setAttribute("orderSession",list);
        return mav;
    }

    /**
     * 根据订单id查询订单详情
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") String id){
        if(StringUtils.isEmpty(id)){
            log.error("【查询订单】id为空");
            throw new CustomException(1,"id为空");
        }
        Order order = orderService.selectById(id);
        if(order!=null){
            return ResultVOUtil.success(order);
        }
        return ResultVOUtil.error("暂时没有订单");
    }

    /**
     * 根据订单id获取订单进度
     * @param id
     * @return
     */
    @GetMapping("/alter/{id}")
    @ResponseBody
    public Result getOrderAlter(@PathVariable("id") String id){
        if(StringUtils.isEmpty(id)){
            log.error("【查询订单】id为空");
            throw new CustomException(1,"id为空");
        }
        List<OrderAlter>  orderAlters = orderAlterService.selectByOrderId(id);
        if(orderAlters.size()>0){
            return ResultVOUtil.success(orderAlters);
        }
        return ResultVOUtil.error("该订单暂时无进度");
    }


    /**
     *  用户获取订单列表
     * @param id
     * @param status
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list/{id}")
    @ResponseBody
    public Result list(@PathVariable("id") String id,
                       @RequestParam(value = "status", defaultValue = "-1") Integer status,
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
            pageDTO = orderService.selectByCustomerId(id, new Byte(status.toString()), page, size);
        }else{
            //查询所有,状态为空表示查询所有
            pageDTO = orderService.selectByCustomerId(id,null,page,size);
        }
        if(pageDTO!=null){
            return ResultVOUtil.success(pageDTO);
        }
        return ResultVOUtil.error("暂时没有数据哦！");
    }
}
