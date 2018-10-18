package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.dto.CommentDTO;
import cn.myzqu.ygmall.dto.PageDTO;
import cn.myzqu.ygmall.pojo.Comment;
import cn.myzqu.ygmall.pojo.Goods;
import cn.myzqu.ygmall.pojo.OrderDetail;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by CC on 2018/9/20.
 */
public interface CommentService {

    BootstrapTableVO findComment(Map<String, Object> map, Integer pageIndex, Integer pageSize);

    int modifyDisplayById(Comment comment);

    int updateDisplayByIdList(List<Integer> idList);

    Comment findComment(String id);

    int modifyStatusById(CommentDTO commentDTO);

    PageDTO selectByGoodsId(String goodsId, Byte goodsScore, Integer pageIndex, Integer pageSize);

    PageDTO selectAddCom(String userId, Integer pageIndex, Integer pageSize);

    PageDTO searchComment(String userId, Integer pageIndex, Integer pageSize);

    int updateAdditComment(Comment comment);

    PageDTO selectComment(String userId,Integer pageIndex,Integer pageSize);
}
