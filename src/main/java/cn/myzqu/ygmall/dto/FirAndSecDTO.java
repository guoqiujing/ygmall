package cn.myzqu.ygmall.dto;

import lombok.Data;

/**
 * 单次购买用户和多次购买用户
 * Created by 小奇冰 on 2018/10/12.
 */
@Data
public class FirAndSecDTO {
    private int fir_buy;

    private int sec_buy;
}
