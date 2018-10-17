package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.dto.CommentDTO;
import cn.myzqu.ygmall.dto.PageDTO;
import cn.myzqu.ygmall.enums.GoodsScoreEnum;
import cn.myzqu.ygmall.exception.CustomException;
import cn.myzqu.ygmall.pojo.Comment;
import cn.myzqu.ygmall.service.CommentService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import cn.myzqu.ygmall.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by CC on 2018/9/20.
 */
@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/list")
    public Result findComment(int pageSize, int pageIndex, Byte goodsScore, Byte commentStatus) {
        System.out.println("每页数据条数：" + pageSize);
        System.out.println("第几页：" + pageIndex);
        System.out.println("要查询的评价等级：" + goodsScore);
        System.out.println("要查询的状态：" + commentStatus);
        //向Map中添加条件
        Map<String, Object> condition = new HashMap<>();
        condition.put("goodsScore", goodsScore);
        condition.put("commentStatus", commentStatus);

        BootstrapTableVO bto = commentService.findComment(condition, pageIndex, pageSize);
        if (bto != null) {
            return ResultVOUtil.success(bto);
        }

        return ResultVOUtil.error("查找评论失败");
    }

    @PostMapping("/hide")
    @ResponseBody
    public Result modifyDisplayById(String id, Byte display) {
        System.out.println(id);
        System.out.println(display);
        Comment comment = new Comment(id, display);
        int b = commentService.modifyDisplayById(comment);
        if (b > 0) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("隐藏评论失败");
    }

    @PostMapping("/batchHide")
    @ResponseBody
    public Result updateDispalyByIdList(Integer idList[]) {
        for (int l : idList) {
            System.out.println(l);
        }
        List<Integer> resultList = new ArrayList<>(Arrays.asList(idList));
        int a = commentService.updateDisplayByIdList(resultList);
        if (a > 0) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("批量隐藏失败");
    }

    @PostMapping("/commentInfo")
    @ResponseBody
    public Result getCommentInfoById(String id) {
        System.out.println(id);
        Comment comment = commentService.findComment(id);
        if (comment != null) {
            return ResultVOUtil.success(comment);
        }
        return ResultVOUtil.error("找不到该评论");
    }

    @PostMapping("/updateStatus")
    @ResponseBody
    public Result updateStatusById(String id, Byte commentStatus) {
        System.out.println(id);
        System.out.println(commentStatus);
        CommentDTO commentDTO = new CommentDTO(id, commentStatus);
        int c = commentService.modifyStatusById(commentDTO);
        System.out.println(c);
        if (c <= 0) {
            return ResultVOUtil.error("更改状态失败");
        }
        return ResultVOUtil.success();
    }

    @GetMapping("/comList/{id}")
    public Result list(@PathVariable("id") String id,
                       @RequestParam(value = "goodsScore", defaultValue = "-1") Integer goodsScore,
                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(id)) {
            log.error("【查询评论列表】id为空");
            throw new CustomException(1, "id为空");
        }
        System.out.println(goodsScore);
        //判断status是否合法
        PageDTO pageDTO = null;
        if (GoodsScoreEnum.FEEDBACK.getCode().equals(goodsScore) ||
                GoodsScoreEnum.REVIEW.getCode().equals(goodsScore) ||
                GoodsScoreEnum.BEST.getCode().equals(goodsScore)) {
            pageDTO = commentService.selectByGoodsId(id, new Byte(goodsScore.toString()), page, size);
        } else {
            pageDTO = commentService.selectByGoodsId(id, null, page, size);
        }
        if (pageDTO != null) {
            return ResultVOUtil.success(pageDTO);
        }
        return ResultVOUtil.error("暂时没有数据哦！");
    }

    @PostMapping("/addComList")
    public Result searchAddCom(String userId,Integer page,Integer size){
        System.out.println(userId);
        PageDTO pageDTO = commentService.selectAddCom(userId, page, size);
        if(pageDTO!=null){
            return ResultVOUtil.success(pageDTO);
        }
        return ResultVOUtil.error("暂时没有数据哦！");
    }
}
