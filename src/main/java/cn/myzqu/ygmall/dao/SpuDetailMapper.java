package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.SpuDetail;

import java.util.List;

public interface SpuDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpuDetail record);

    int insertSelective(SpuDetail record);

    SpuDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpuDetail record);

    int updateByPrimaryKey(SpuDetail record);

    List<SpuDetail> selectBySPUId(String spuId);
}