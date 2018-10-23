package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.CustomerMapper;
import cn.myzqu.ygmall.dao.GoodsMapper;
import cn.myzqu.ygmall.dto.OrderDTO;
import cn.myzqu.ygmall.pojo.Customer;
import cn.myzqu.ygmall.pojo.CustomerAddress;
import cn.myzqu.ygmall.service.OrderService;
import cn.myzqu.ygmall.utils.gen.UserInfo;
import cn.myzqu.ygmall.vo.Goods_Img_AttributesVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by catt on 2018/10/23.
 */



@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/*.xml")
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    public void add() throws Exception {

        //获取系统所有用户
        List<Customer> customers = customerMapper.selectCustomer(null);

        //获取数据库中所有的商品
        List<Goods_Img_AttributesVO> goods = goodsMapper.getAll(null);

        for(int i = 0;i<100;i++){
            //随机获取用户，获取商品
            List<OrderDTO> orderDTOList = new ArrayList<>();
            OrderDTO orderDTO = new OrderDTO();
            Random ra =new Random();
            //随机获取用户
            int userIndex = ra.nextInt(customers.size()-1)+1;
            Customer customer = customers.get(userIndex);
            orderDTO.setUserId(customer.getId());

            //随机获取商品
            int goodsIndex = ra.nextInt(goods.size()-1)+1;
            Goods_Img_AttributesVO good = goods.get(goodsIndex);

            orderDTO.setGoodsNumber(good.getId());
            orderDTO.setGoodsName(good.getName());
          //  orderDTO.setFormatAndStyle(good.);
            orderDTO.setMarketPrice(good.getMarketPrice());
            orderDTO.setPrice(good.getPrice());
            orderDTO.setCount(1);

            orderDTOList.add(orderDTO);

            //随机生成地址
            CustomerAddress customerAddress = new CustomerAddress();
            customerAddress.setUserId(customer.getId());
            customerAddress.setReceiverName(customer.getNickName());
            customerAddress.setReceiverTel(customer.getTelephone());
            customerAddress.setProvince("广东");
            customerAddress.setCity("佛山");
            customerAddress.setCounty("禅城区");
            customerAddress.setDetail(UserInfo.getRoad());
            orderService.add(customerAddress,orderDTOList);
        }

    }

    @Test
    public void buy() throws Exception {

    }

    @Test
    public void cancel() throws Exception {

    }

    @Test
    public void deliver() throws Exception {

    }

    @Test
    public void receive() throws Exception {

    }

}