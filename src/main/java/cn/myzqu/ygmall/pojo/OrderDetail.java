package cn.myzqu.ygmall.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDetail {
    private String id;

    private String orderId;

    private String userId;

    private String goodsNumber;

    private String goodsName;

    private String formatAndStyle;

    private BigDecimal marketPrice;

    private BigDecimal price;

    private Integer count;

    private Byte status;

    private Date createTime;

}