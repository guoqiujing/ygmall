package cn.myzqu.ygmall.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by 的川 on 2018/10/20.
 */
@Data
public class GoodsDTO {

    //商品id
    private String id;
    //产品id
    private String spu;
    //商品名称
    private String name;
    //商品图片
    private String img;
    //商品价格
    private String price;
    //市场价
    private String market;
    private String url;


}
