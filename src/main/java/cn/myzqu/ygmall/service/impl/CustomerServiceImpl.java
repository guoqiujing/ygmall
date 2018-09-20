package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.CustomerMapper;
import cn.myzqu.ygmall.pojo.Customer;
import cn.myzqu.ygmall.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 用户信息类服务接口实现类
 * Created by 小奇冰 on 2018/9/19.
 */
@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer findCustomerById(String id){
        Customer customer= customerMapper.selectByPrimaryKey(id);
        return customer;
    }

    @Override
    public int updateCustomerById(Customer customer){
        int c =customerMapper.updateByPrimaryKeySelective(customer);
        return c;
    }
}
