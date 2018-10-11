package cn.myzqu.ygmall.vo;

import cn.myzqu.ygmall.pojo.Goods;
import cn.myzqu.ygmall.pojo.Spu;
import cn.myzqu.ygmall.pojo.SpuDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Simon on 2018/9/18.
 */
@JsonIgnoreProperties(value = { "handler" })
public class SpuDetailVO{
    private String categoriesName;
    private String brandName;
    private List<SpuDetail> spuImgList;
    private Spu spu;

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<SpuDetail> getSpuImgList() {
        return spuImgList;
    }

    public void setSpuImgList(List<SpuDetail> spuImgList) {
        this.spuImgList = spuImgList;
    }

    @Override
    public String toString() {
        return "SpuDetailVO{" +
                "categoriesName='" + categoriesName + '\'' +
                ", brandName='" + brandName + '\'' +
                ", spuImgList=" + spuImgList +
                ", spu=" + spu +
                '}';
    }
}
