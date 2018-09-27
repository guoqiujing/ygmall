package cn.myzqu.ygmall.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    private String id;

    private String userId;

    private BigDecimal goodsTotalMoney;

    private BigDecimal carriage;

    private BigDecimal realTotalMoney;

    private String receiverName;

    private String receiverTel;

    private String province;

    private String city;

    private String county;

    private String detail;

    private String orderRemarks;

    private Byte attributes;

    private Byte status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private List<OrderDetail> orderDetails;
}