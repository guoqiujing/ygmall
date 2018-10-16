package cn.myzqu.ygmall.enums;

/**
 * Created by CC on 2018/10/10.
 */
public enum GoodsScoreEnum {
    /**
     * 评价类型（-1，全部评价；0：差评；1：中评；2：好评）
     */
    ALL(-1, "全部评价"),
    FEEDBACK(0, "差评"),
    REVIEW(1, "中评"),
    BEST(2,"好评"),
    ;
    private Integer code;
    private String message;

    GoodsScoreEnum(Integer code, String message) {
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
