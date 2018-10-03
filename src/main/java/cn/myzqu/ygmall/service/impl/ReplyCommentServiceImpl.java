package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.ReplyCommentMapper;
import cn.myzqu.ygmall.pojo.ReplyComment;
import cn.myzqu.ygmall.service.ReplyCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by CC on 2018/9/27.
 */
@Service
@Slf4j
public class ReplyCommentServiceImpl implements ReplyCommentService{
    @Autowired
    private ReplyCommentMapper replyCommentMapper;

    //根据评论id查找回复
    @Override
    public ReplyComment findReplyComment(String id){
        ReplyComment replyComment=replyCommentMapper.selectByCommentId(id);
        return replyComment;
    }

    //添加回复
    @Override
    public int addReply(ReplyComment replyComment){
        int r=replyCommentMapper.insertSelective(replyComment);
        return r;
    }
    //添加追评回复
    @Override
    public int addContent(ReplyComment replyComment){
        int r=replyCommentMapper.insertSelective(replyComment);
        return r;
    }

    //根据回复id添加追评回复
    @Override
    public int updateContent(ReplyComment replyComment){
        int r=replyCommentMapper.updateByPrimaryKeySelective(replyComment);
        return r;
    }
}
