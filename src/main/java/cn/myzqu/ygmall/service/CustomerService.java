package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.Customer;

/**
 * Created by 小奇冰 on 2018/9/19.
 */
public interface CustomerService {

    Customer findCustomerById(String id);

    int updateCustomerById(Customer customer);
}
