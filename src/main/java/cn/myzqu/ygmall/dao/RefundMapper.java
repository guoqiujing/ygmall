package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.Refund;

import java.util.List;
import java.util.Map;

public interface RefundMapper {
    int deleteByPrimaryKey(String id);

    int insert(Refund record);

    int insertSelective(Refund record);

    Refund selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Refund record);

    int updateByPrimaryKey(Refund record);

    List<Refund> selectRefund(Map<String,Object> map);
}