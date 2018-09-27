package cn.myzqu.ygmall.enums;

/**
 * Created by 的川 on 2018/9/27.
 */
public enum OrderStatusEnum {

    /**
     * 订单状态（-1，全部订单；0：待付款；1：待发货；2：待收货；3；已完成；4：已取消）
     */
    ALL(-1,"全部订单"),
    OBLIGATION(0,"待付款"),
    DAIFAHUO(1,"待发货"),
    DAISHOUHUO(2,"待收货"),
    DONE(3,"已完成"),
    CANCELED(4,"已取消"),
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
