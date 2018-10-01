package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.dto.ReplyCommentDTO;
import cn.myzqu.ygmall.pojo.ReplyComment;

public interface ReplyCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReplyComment record);

    int insertSelective(ReplyComment record);

    ReplyComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReplyComment record);

    int updateByPrimaryKey(ReplyComment record);

    ReplyComment selectByCommentId(String commentId);

    int insertContent(ReplyCommentDTO replyCommentDTO);
}