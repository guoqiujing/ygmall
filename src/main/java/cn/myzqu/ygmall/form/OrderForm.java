package cn.myzqu.ygmall.form;

import lombok.Data;

/**
 * Created by 的川 on 2018/9/25.
 */
@Data
public class OrderForm {

    //获取地址id
    private String address;

    //购物车串，Json封装信息过来
    private String items;


}
