package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.dto.SexCountDTO;
import cn.myzqu.ygmall.pojo.Customer;
import cn.myzqu.ygmall.vo.BootstrapTableVO;

import java.util.Map;

/**
 * Created by 小奇冰 on 2018/9/19.
 */
public interface CustomerService {

    BootstrapTableVO findCustomer(Map<String, Object> map, Integer pageIndex, Integer pageSize);

    Customer findCustomerById(String id);

    int updateCustomerById(Customer customer);

    SexCountDTO findSexCount();
}
