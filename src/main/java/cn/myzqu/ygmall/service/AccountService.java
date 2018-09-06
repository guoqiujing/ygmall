package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.Customer;

/**
 * Created by 的川 on 2018/9/6.
 */

public interface AccountService {

    Customer addCustomer(String telephone, String email, String password);

    Boolean login(String code,String password);

}
