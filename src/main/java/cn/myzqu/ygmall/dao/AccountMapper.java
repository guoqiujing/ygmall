package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.Account;

public interface AccountMapper {
    int deleteByPrimaryKey(String id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}