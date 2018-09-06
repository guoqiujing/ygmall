package cn.myzqu.ygmall.exception;

import cn.myzqu.ygmall.enums.ResultEnum;

/**
 * 系统自定义异常
 * Created by 的川 on 2018/5/8.
 */
public class CustomException extends RuntimeException{

    /**
     * 异常码
     */
    private Integer code;

    /**
     * 构造方法
     * @param code
     * @param message
     */
    public CustomException(Integer code,String message){
        super(message);
        this.code = code;
    }

    /**
     * 构造方法
     * @param resultEnum
     */
    public CustomException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

}
