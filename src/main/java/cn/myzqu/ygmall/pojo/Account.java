package cn.myzqu.ygmall.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Account {
    private String id;

    private String password;

    private String salt;

    private Byte type;

    private Byte state;

    private Date createTime;

    private Date loginTime;

}