package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.GoodsImg;

import java.util.List;

/**
 * Created by Simon on 2018/9/20.
 */
public interface GoodsImgService {
    List<GoodsImg> selectByGoodsId(String goodsId);
    public Integer insert(String spuId,String urlList);
    public Integer update(String goodsId,String urlList);
    public Integer delete(String goodsId);
}
