package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.CustomerMapper;
import cn.myzqu.ygmall.pojo.Customer;
import cn.myzqu.ygmall.service.CustomerService;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 用户信息类服务接口实现类
 * Created by 小奇冰 on 2018/9/19.
 */
@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public BootstrapTableVO findCustomer(Map<String, Object> map, Integer pageIndex, Integer pageSize) {
        //分页插件
        Page<Customer> page = PageHelper.startPage(pageIndex,pageSize);
        List<Customer> brandList = customerMapper.selectCustomer(map);
        //获取总记录数
        int total = (int)page.getTotal();
        System.out.println("总记录数："+total);
        //把总记录数和某一页的记录装入BootstrapTableVO类
        BootstrapTableVO bto=new BootstrapTableVO(total,brandList);
        if(total<=0)
            return null;
        return bto;
    }

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
