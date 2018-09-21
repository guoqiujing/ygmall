package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.GoodsImgMapper;
import cn.myzqu.ygmall.pojo.GoodsImg;
import cn.myzqu.ygmall.service.GoodsImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Simon on 2018/9/20.
 */
@Service
public class GoodsImgServiceImpl implements GoodsImgService {
    @Autowired
    private GoodsImgMapper goodsImgMapper;
    @Override
    public List<GoodsImg> selectByGoodsId(String goodsId) {
        List<GoodsImg> goodsImgs=goodsImgMapper.selectByGoodsId(goodsId);
        return goodsImgs;
    }
}
