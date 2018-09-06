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

    public Account() {
    }

    public Account(String id, String password, String salt) {
        this.id = id;
        this.password = password;
        this.salt = salt;
    }

    public Account(String id, String password, String salt, Byte type, Byte state) {
        this.id = id;
        this.password = password;
        this.salt = salt;
        this.type = type;
        this.state = state;
    }
}