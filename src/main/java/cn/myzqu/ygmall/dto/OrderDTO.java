package cn.myzqu.ygmall.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by 的川 on 2018/9/27.
 */
@Data
public class OrderDTO {

//    private String id;
//    private String name;
//    private Double price;
//    private String attributes;
//    private Integer count;

    private String userId;
    private String goodsNumber;
    private String goodsName;
    private String goodsPicture;
    private String formatAndStyle;
    private BigDecimal marketPrice;
    private BigDecimal price;
    private Integer count;
    private Double sum;


}
