package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.GoodsImg;

import java.util.List;

/**
 * Created by Simon on 2018/9/20.
 */
public interface GoodsImgService {
    List<GoodsImg> selectByGoodsId(String goodsId);

}
