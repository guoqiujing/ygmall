package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.ReplyComment;

/**
 * Created by CC on 2018/9/27.
 */
public interface ReplyCommentService {
    ReplyComment findReplyComment(String id);

    ReplyComment addReply(String commentId,String replyContent);

    ReplyComment addContent(String commentId,String content);

    ReplyComment updateContent(String id,String content);
}
