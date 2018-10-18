package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.dto.CommentDTO;
import cn.myzqu.ygmall.pojo.Comment;
import cn.myzqu.ygmall.pojo.OrderDetail;
import cn.myzqu.ygmall.vo.CommentReplyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    int updateDisplayByPrimaryKey(Comment comment);

    List<Comment> selectComment(Map<String, Object> map);

    int  updateByIdList(List<Integer> idList);

    int updateStatusById(CommentDTO record);

    List<CommentReplyVO> selectByGoodsId(@Param("goodsId") String goodsId, @Param("goodsScore") Byte goodsscore);

    List<OrderDetail> selectAddCom(String userId);

    List<OrderDetail> selectCommentByOrder(String userId);
}