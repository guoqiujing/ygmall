package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.GoodsImg;

public interface GoodsImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsImg record);

    int insertSelective(GoodsImg record);

    GoodsImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsImg record);

    int updateByPrimaryKey(GoodsImg record);
}