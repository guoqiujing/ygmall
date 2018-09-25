package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.Cart;
import cn.myzqu.ygmall.pojo.CartExample;

public interface CartMapper {
    int countByExample(CartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}