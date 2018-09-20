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

    public Customer(String id, String nickName, String icon, Byte sex, String telephone, String email) {
        this.id = id;
        this.nickName = nickName;
        this.icon = icon;
        this.sex = sex;
        this.telephone = telephone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                ", icon='" + icon + '\'' +
                ", sex=" + sex +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}