package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.Goods;
import cn.myzqu.ygmall.vo.GoodsDetailVO;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<GoodsDetailVO> getGoodsAndSPU(String id);

    List<Goods> getIdAndAttributes(String spuId);
}