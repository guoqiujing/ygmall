package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.Comment;
import cn.myzqu.ygmall.vo.BootstrapTableVO;

import java.util.List;
import java.util.Map;

/**
 * Created by CC on 2018/9/20.
 */
public interface CommentService {
    /**
     * 查找评论
     * @return
     */
    BootstrapTableVO findComment(Map<String, Object> map, Integer pageIndex, Integer pageSize);

    /**
     * 根据id 隐藏评论
     */
    int modifyDisplayById(Comment comment);

    /**
     * 批量隐藏评论
     * @param idList
     * @return
     */
    int updateDisplayByIdList(List<Integer> idList);
}
