package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.pojo.ReplyComment;
import cn.myzqu.ygmall.service.ReplyCommentService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public Result addReply(String commentId,String replyContent){
        ReplyComment replyComment=replyCommentService.addReply(commentId,replyContent);
        if(replyComment!=null){
            return ResultVOUtil.success(replyComment);
        }
        return ResultVOUtil.error("回复失败");
    }


    /**
     * 添加追评回复
     * @param commentId
     * @param content
     * @return
     */
    @PostMapping("/addContent")
    @ResponseBody
    public Result addContent(String commentId,String content){
        ReplyComment replyComment=replyCommentService.addContent(commentId,content);
        if(replyComment!=null){
            return ResultVOUtil.success(replyComment);
        }
        return null;
    }

    @PostMapping("/updateContent")
    @ResponseBody
    public Result updateContent(String id,String content){
        System.out.println(id);
        ReplyComment replyComment=replyCommentService.updateContent(id,content);
        if(replyComment!=null){
            return ResultVOUtil.success(replyComment);
        }
        return null;
    }
}
