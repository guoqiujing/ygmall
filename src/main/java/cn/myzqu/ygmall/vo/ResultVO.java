package cn.myzqu.ygmall.vo;

import lombok.Data;


/**
 * Created by 的川 on 2018/7/16.
 */
@Data
public class ResultVO<T> extends Result{


    /**
     * 具体内容
     */
    private T data;


}
