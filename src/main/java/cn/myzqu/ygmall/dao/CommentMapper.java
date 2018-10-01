package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.dto.CommentDTO;
import cn.myzqu.ygmall.pojo.Comment;

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
}