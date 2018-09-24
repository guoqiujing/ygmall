package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.CustomerAddressMapper;
import cn.myzqu.ygmall.dao.CustomerMapper;
import cn.myzqu.ygmall.pojo.CustomerAddress;
import cn.myzqu.ygmall.service.CustomerAddressService;
import cn.myzqu.ygmall.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 小奇冰 on 2018/9/21.
 */
@Service
public class CustomerAddressServiceImpl implements CustomerAddressService{
    @Autowired
    private CustomerAddressMapper customerAddressMapper;

    /**
     * 根据用户id查找收货地址
     * @param userId
     * @return
     */
    @Override
    public List<CustomerAddress>  findByUserId(String userId){
        List<CustomerAddress> customerAddressList=customerAddressMapper.selectByUserId(userId);
        if(customerAddressList.isEmpty()){
            return null;
        }
        return customerAddressList;
    }

    /**
     * 增加收货地址
     * @param customerAddress
     * @return
     */
    @Override
    public int addAddress(CustomerAddress customerAddress){
        String id=KeyUtil.getUUID();
        customerAddress.setId(id);
        int c=customerAddressMapper.insertSelective(customerAddress);
        return c;
    }

    /**
     * 修改收货地址
     * @param customerAddress
     * @return
     */
    @Override
    public int updateAddress(CustomerAddress customerAddress){
        int c=customerAddressMapper.updateByPrimaryKeySelective(customerAddress);
        return c;
    }

    /**
     * 删除收货地址
     * @param id
     * @return
     */
    @Override
    public int deleteAddress(String id){
        int c=customerAddressMapper.deleteByPrimaryKey(id);
        return c;
    }
}
