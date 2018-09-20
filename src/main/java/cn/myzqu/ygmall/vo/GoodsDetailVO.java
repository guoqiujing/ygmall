package cn.myzqu.ygmall.vo;

import cn.myzqu.ygmall.pojo.Goods;
import cn.myzqu.ygmall.pojo.Spu;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Simon on 2018/9/18.
 */
@JsonIgnoreProperties(value = { "handler" })
public class GoodsDetailVO extends Goods{
    private Spu spu;

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    @Override
    public String toString() {
        return "GoodsDetailVO{" +
                "spu=" + spu +
                "Goods=" + super.toString() +
                "spu=" + spu +
                '}';
    }
}
