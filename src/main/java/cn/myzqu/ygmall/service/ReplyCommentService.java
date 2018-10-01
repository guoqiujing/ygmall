package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.dto.ReplyCommentDTO;
import cn.myzqu.ygmall.pojo.ReplyComment;

/**
 * Created by CC on 2018/9/27.
 */
public interface ReplyCommentService {
    ReplyComment findReplyComment(String id);

    int addReply(ReplyComment replyComment);

    int updateContent(ReplyComment replyComment);

    int addContent(ReplyCommentDTO replyCommentDTO);
}
