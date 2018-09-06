package cn.myzqu.ygmall.enums;

/**
 * Created by 的川 on 2018/5/8.
 */
public enum ResultEnum {
    SUCCESS(0,"成功"),
    ERROR(-1,"哎呀，发生异常啦，请稍后重试！"),
    PARAMETER_ERROR(-2,"哎呀，请求参数错误啦"),
    SQL_ERROR(-3,"哎呀，操作数据异常啦"),
    LOGIN_FAIL(1000,"登录失败，登录信息不正确"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
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
