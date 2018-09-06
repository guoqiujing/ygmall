package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.CustomerAddress;

public interface CustomerAddressMapper {
    int deleteByPrimaryKey(String id);

    int insert(CustomerAddress record);

    int insertSelective(CustomerAddress record);

    CustomerAddress selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CustomerAddress record);

    int updateByPrimaryKey(CustomerAddress record);
}