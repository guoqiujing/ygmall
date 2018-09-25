package cn.myzqu.ygmall.service;


import cn.myzqu.ygmall.pojo.Cart;
import cn.myzqu.ygmall.vo.GoodsDetailVO;

import java.util.HashMap;
import java.util.List;

public interface CartService {
    List<Cart> findAllCart();

}
