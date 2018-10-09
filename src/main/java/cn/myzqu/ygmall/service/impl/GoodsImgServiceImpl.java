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
    public Integer insert(String goodsId,String urlList) {
        String[] urls=urlList.split(",");
        int i;
        for(i=0;i<urls.length;i++){
            GoodsImg goodsImg=new GoodsImg();
            goodsImg.setImgOrder(i);
            goodsImg.setImgUrl(urls[i]);
            goodsImg.setGoodsId(goodsId);
            if(goodsImgMapper.insert(goodsImg)!=1)
                break;
        }
        if(i!=urls.length)
            return null;
        return 1;
    }
    public Integer update(String goodsId,String urlList) {
        Integer delResult=delete(goodsId);
        System.out.println("----------------------");
        System.out.println("delResult:"+delResult);
        System.out.println("----------------------");
        Integer result=0;
        if(delResult>0)
            result=insert(goodsId,urlList);
        System.out.println("----------------------");
        System.out.println("insertResult:"+result);
        System.out.println("----------------------");
        return result;
    }
    public Integer delete(String goodsId) {
        Integer result=goodsImgMapper.deleteByGoodsId(goodsId);
        return result;
    }
}
