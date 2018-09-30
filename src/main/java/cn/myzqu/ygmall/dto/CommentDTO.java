package cn.myzqu.ygmall.dto;

import lombok.Data;

/**
 * Created by CC on 2018/9/29.
 */
@Data
public class CommentDTO {
    private String id;
    private Byte commentStatus;

    /**
     * 构造方法
     * @param id
     * @param commentStatus
     */
    public CommentDTO(String id, Byte commentStatus) {
        this.id = id;
        this.commentStatus = commentStatus;
    }
}
