package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.pojo.Comment;
import cn.myzqu.ygmall.pojo.ReplyComment;
import cn.myzqu.ygmall.service.ReplyCommentService;
import cn.myzqu.ygmall.utils.KeyUtil;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by CC on 2018/9/27.
 */
@RestController
@RequestMapping("/replyComment")
public class ReplyCommentController {
    @Autowired
    private ReplyCommentService replyCommentService;


    /**
     * 根据评论id查找评论
     * @param id
     * @return
     */
    @PostMapping("/replyInfo")
    @ResponseBody
    public Result getReplyInfoByCommentId(String id){
        ReplyComment replyComment=replyCommentService.findReplyComment(id);
        if(replyComment!=null)
            return ResultVOUtil.success(replyComment);
        return ResultVOUtil.error("找不到该评论的回复");
    }

    /**
     *添加回复
     *  @param commentId
     * @param replyContent
     * @return
     */
    @PostMapping("/addReply")
    @ResponseBody
    public Result addReply(String commentId,String replyName,String replyContent){
       String id=KeyUtil.getUUID();
       ReplyComment replyComment=new ReplyComment(id,commentId,replyName,replyContent);
       int r=replyCommentService.addReply(replyComment);
       if(r<=0){
           return ResultVOUtil.error("回复失败");
       }
        return  ResultVOUtil.success();
    }

    /**
     * 添加追评回复
     * @param commentId
     * @param content
     * @return
     */
    @PostMapping("/addContent")
    @ResponseBody
    public Result addContent(String commentId,String replyName,String content){
        String id= KeyUtil.getUUID();
        ReplyComment replyComment=new ReplyComment();
        replyComment.setId(id);
        replyComment.setReplyId(commentId);
        replyComment.setReplyName(replyName);
        replyComment.setContent(content);
        int r=replyCommentService.addContent(replyComment);
        if(r<=0){
            return  ResultVOUtil.error("添加追评回复失败");
        }
        return  ResultVOUtil.success();
    }

    /**
     * 根据回复id添加追评回复
     * @param id
     * @param content
     * @return
     */
    @PostMapping("/updateContent")
    @ResponseBody
    public Result updateContent(String id,String content){
        System.out.println(id);
        ReplyComment replyComment=new ReplyComment(id,content);
        int r=replyCommentService.updateContent(replyComment);
        if(r<=0){
            return  ResultVOUtil.error("更改追评回复错误");

        }
        return ResultVOUtil.success();
    }
}
