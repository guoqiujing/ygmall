package cn.myzqu.ygmall.vo;

import cn.myzqu.ygmall.pojo.GoodsImg;
import cn.myzqu.ygmall.pojo.OrderDetail;

/**
 * Created by CC on 2018/10/19.
 */
public class OrdDetGoodsImgVO extends OrderDetail{
    private GoodsImg goodsImg;

    public GoodsImg getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(GoodsImg goodsImg) {
        this.goodsImg = goodsImg;
    }
}
