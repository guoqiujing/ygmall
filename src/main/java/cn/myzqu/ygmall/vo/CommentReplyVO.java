package cn.myzqu.ygmall.vo;

import cn.myzqu.ygmall.pojo.Comment;
import cn.myzqu.ygmall.pojo.ReplyComment;

/**
 * Created by CC on 2018/10/11.
 */
public class CommentReplyVO extends Comment{
    private ReplyComment replyComment;

    public ReplyComment getReplyComment() {
        return replyComment;
    }

    public void setReplyComment(ReplyComment replyComment) {
        this.replyComment = replyComment;
    }
}
