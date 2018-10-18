package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.dto.CommentDTO;
import cn.myzqu.ygmall.dto.PageDTO;
import cn.myzqu.ygmall.pojo.Comment;
import cn.myzqu.ygmall.pojo.Goods;
import cn.myzqu.ygmall.pojo.OrderDetail;
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

    /**
     *根据评论id查询评论
     *  @param id
     * @return
     */
    Comment findComment(String id);


    /**
     *根据评论id更新回复状态
     *  @param commentDTO
     * @return
     */
   int modifyStatusById(CommentDTO commentDTO);

    /**
     *用户查看商品详情页的评论和商家回复
     *  @param goodsId
     * @param goodsScore
     * @param pageIndex
     * @param pageSize
     * @return
     */
   PageDTO selectByGoodsId(String goodsId, Byte goodsScore, Integer pageIndex, Integer pageSize);

    /**
     * 根据用户ID查询用户是否可以追评
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @return
     */
   PageDTO selectAddCom(String userId,Integer pageIndex,Integer pageSize);

    /**
     * 根据订单详情表的信息查询用户是否可以评论
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @return
     */
   PageDTO searchComment(String userId,Integer pageIndex,Integer pageSize);
}
