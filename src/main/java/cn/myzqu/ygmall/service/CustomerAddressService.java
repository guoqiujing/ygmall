package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.CustomerAddress;

import java.util.List;

/**
 * Created by 小奇冰 on 2018/9/21.
 */
public interface CustomerAddressService {

    CustomerAddress findById(String id);

    List<CustomerAddress> findByUserId(String userId);

    int addAddress(CustomerAddress customerAddress);

    int updateAddress(CustomerAddress customerAddress);

    int deleteAddress(String id);
}
