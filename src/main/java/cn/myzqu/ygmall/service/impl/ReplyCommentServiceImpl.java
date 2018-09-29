package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.ReplyCommentMapper;
import cn.myzqu.ygmall.pojo.ReplyComment;
import cn.myzqu.ygmall.service.ReplyCommentService;
import cn.myzqu.ygmall.utils.DateUtil;
import cn.myzqu.ygmall.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by CC on 2018/9/27.
 */
@Service
@Slf4j
public class ReplyCommentServiceImpl implements ReplyCommentService{
    @Autowired
    private ReplyCommentMapper replyCommentMapper;

    //根据评论id查找评论
    @Override
    public ReplyComment findReplyComment(String id){
        ReplyComment replyComment=replyCommentMapper.selectByCommentId(id);
        return replyComment;
    }

    //添加回复
    @Override
    public ReplyComment addReply(String commentId,String replyContent){
        String id= KeyUtil.getUUID();
        ReplyComment replyComment=new ReplyComment(id,commentId,replyContent);
        if(replyCommentMapper.insertSelective(replyComment)>0){
            log.info("回复成功：{}",replyComment);
            return replyComment;
        }
        return null;
    }
    //添加追评回复
    @Override
    public ReplyComment addContent(String commentId,String content){
        String id=KeyUtil.getUUID();
        Date time=new Date();
        ReplyComment replyComment=new ReplyComment(id,commentId,content,time);
        if(replyCommentMapper.insertSelective(replyComment)>0){
            log.info("回复追评成功：{}",replyComment);
            return replyComment;
        }
        return null;
    }

    //根据回复id添加追评回复
    @Override
    public ReplyComment updateContent(String id,String content){
        ReplyComment replyComment=new ReplyComment(id,content);
        if(replyCommentMapper.updateByPrimaryKeySelective(replyComment)>0){
            log.info("回复追评成功：{}",replyComment);
            return replyComment;
        }
        return null;
    }
}
