package cn.myzqu.ygmall.vo;

import cn.myzqu.ygmall.pojo.Comment;
import cn.myzqu.ygmall.pojo.Goods;

/**
 * Created by CC on 2018/10/17.
 */
public class CommentGoodsVO extends Comment{
    private Goods goods;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
