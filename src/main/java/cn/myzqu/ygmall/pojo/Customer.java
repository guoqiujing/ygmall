package cn.myzqu.ygmall.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Customer {
    private String id;

    private String nickName;

    private String icon;

    private Byte sex;

    private String telephone;

    private String email;

    private Date createTime;

    private Date updateTime;

    public Customer() {
    }

    public Customer(String id, String telephone, String email) {
        this.id = id;
        this.telephone = telephone;
        this.email = email;
    }
}