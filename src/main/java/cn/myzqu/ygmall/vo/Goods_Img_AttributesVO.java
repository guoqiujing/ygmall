package cn.myzqu.ygmall.vo;

import cn.myzqu.ygmall.pojo.Goods;
import cn.myzqu.ygmall.pojo.GoodsImg;
import cn.myzqu.ygmall.pojo.Spu;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Simon on 2018/9/18.
 */
@JsonIgnoreProperties(value = { "handler" })
public class Goods_Img_AttributesVO extends Goods{
    private List<GoodsImg> goodsImgList;
    private CategoriesAttributeVO categoriesAttributeVO;//商品所属分类完整路径及其所含规格值

    public List<GoodsImg> getGoodsImgList() {
        return goodsImgList;
    }

    public void setGoodsImgList(List<GoodsImg> goodsImgList) {
        this.goodsImgList = goodsImgList;
    }

    public CategoriesAttributeVO getCategoriesAttributeVO() {
        return categoriesAttributeVO;
    }

    public void setCategoriesAttributeVO(CategoriesAttributeVO categoriesAttributeVO) {
        this.categoriesAttributeVO = categoriesAttributeVO;
    }

    @Override
    public String toString() {
        return "Goods_Img_AttributesVO{" +
                "Goods=" + super.toString() +
                "goodsImgList=" + goodsImgList +
                ", categoriesAttributeVO=" + categoriesAttributeVO +
                '}';
    }

}
