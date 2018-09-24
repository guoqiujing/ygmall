package cn.myzqu.ygmall.dto;

import lombok.Data;

/**
 * Created by 小奇冰 on 2018/9/23.
 */
@Data
public class UserSessionDTO {
    private String id;

    private Byte type;

    private Byte state;

    private String nickName;

    private String icon;

    private Byte sex;

    private String telephone;

    private String email;

    @Override
    public String toString() {
        return "UserSessionDTO{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", nickName='" + nickName + '\'' +
                ", icon='" + icon + '\'' +
                ", sex=" + sex +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
