package cn.myzqu.ygmall.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class OrderAlter {
    private String id;

    private String entryId;

    private Byte state;

    private String operator;

    private Date createTime;

}