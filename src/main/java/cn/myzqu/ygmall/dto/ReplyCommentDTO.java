package cn.myzqu.ygmall.dto;

import lombok.Data;

/**
 * Created by CC on 2018/9/30.
 */
@Data
public class ReplyCommentDTO {
    private String id;

    private String replyId;

    private String content;

    /**
     * 构造方法
     * @param id
     * @param replyId
     * @param content
     */
    public ReplyCommentDTO(String id, String replyId, String content) {
        this.id = id;
        this.replyId = replyId;
        this.content = content;
    }
}
