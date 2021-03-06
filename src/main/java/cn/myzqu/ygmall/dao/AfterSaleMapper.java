package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.dto.PageDTO;
import cn.myzqu.ygmall.pojo.AfterSale;
import cn.myzqu.ygmall.vo.AfterSaleRefundVO;

import java.util.List;
import java.util.Map;

public interface AfterSaleMapper {
    int deleteByPrimaryKey(String id);

    int insert(AfterSale record);

    int insertSelective(AfterSale record);

    AfterSale selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AfterSale record);

    int updateByPrimaryKey(AfterSale record);

    List<AfterSale> selectAfterSale(Map<String,Object> map);

    List<AfterSaleRefundVO> selectAfterSaleByUserId(String userId);
}