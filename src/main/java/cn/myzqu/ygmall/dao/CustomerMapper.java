package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.Customer;

public interface CustomerMapper {

    int deleteByPrimaryKey(String id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(String id);

    Customer selectByTelephone(String telephone);

    Customer selectByEmail(String email);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}