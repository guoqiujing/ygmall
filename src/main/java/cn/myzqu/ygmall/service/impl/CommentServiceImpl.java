package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.CommentMapper;
import cn.myzqu.ygmall.dto.CommentDTO;
import cn.myzqu.ygmall.dto.PageDTO;
import cn.myzqu.ygmall.pojo.Comment;
import cn.myzqu.ygmall.pojo.OrderDetail;
import cn.myzqu.ygmall.service.CommentService;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import cn.myzqu.ygmall.vo.ComemntOrderDetailVo;
import cn.myzqu.ygmall.vo.CommentReplyVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by CC on 2018/9/20.
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    //查找评论
    @Override
    public BootstrapTableVO findComment(Map<String, Object> map, Integer pageIndex, Integer pageSize){
        //分页插件
        Page<Comment> page = PageHelper.startPage(pageIndex,pageSize);
        List<Comment> commentList=commentMapper.selectComment(map);
        //获取总记录数
        int total = (int)page.getTotal();
        System.out.println("总记录数："+total);
        //把总记录数和某一页的记录装入BootstrapTableVO类
        BootstrapTableVO bto=new BootstrapTableVO(total,commentList);
        if(total<=0)
            return null;

        return bto;
    }

    //隐藏评论
    @Override
    public int modifyDisplayById(Comment comment){
        int a=commentMapper.updateDisplayByPrimaryKey(comment);
        return a;
    }

    //批量隐藏
    @Override
    public  int updateDisplayByIdList(List<Integer> idList){
        int c=commentMapper.updateByIdList(idList);
        return c;
    }

    @Override
    public Comment findComment(String id){
        Comment comment=commentMapper.selectByPrimaryKey(id);
        return comment;
    }

    @Override
    public int modifyStatusById(CommentDTO commentDTO){
        int c=commentMapper.updateStatusById(commentDTO);
        return c;
    }

    @Override
    public PageDTO selectByGoodsId(String goodsId, Byte goodsScore, Integer pageIndex, Integer pageSize) {

        Page<CommentReplyVO> page =PageHelper.startPage(pageIndex,pageSize);
        List<CommentReplyVO> comments=commentMapper.selectByGoodsId(goodsId,goodsScore);

        int total=(int)page.getTotal();
        System.out.println("总记录数："+total);
        if(total<=0)
            return null;
        return new PageDTO(comments,total,pageSize,pageIndex);
    }

    @Override
    public PageDTO selectAddCom(String userId,Integer pageIndex,Integer pageSize){
        Page<OrderDetail> page=PageHelper.startPage(pageIndex,pageSize);
        List<OrderDetail> comments=commentMapper.selectAddCom(userId);

        int total=(int)page.getTotal();
        System.out.println("总记录数："+total);
        if(total<=0)
            return null;
        return new PageDTO(comments,total,pageSize,pageIndex);
    }

    @Override
    public PageDTO searchComment(String userId,Integer pageIndex,Integer pageSize){
        Page<ComemntOrderDetailVo > page=PageHelper.startPage(pageIndex,pageSize);
        List<ComemntOrderDetailVo> orderDetails=commentMapper.selectCommentByOrder(userId);
        int total=(int)page.getTotal();
        System.out.println("总记录数："+total);
        if(total<=0)
            return null;
        return new PageDTO(orderDetails,total,pageSize,pageIndex);
    }
}
