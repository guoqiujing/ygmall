package cn.myzqu.ygmall.dto;

import lombok.Data;

/**
 * Created by 的川 on 2018/9/27.
 */
@Data
public class OrderDTO {

    private String id;
    private String name;
    private Double price;
    private String attributes;
    private Integer count;


}
