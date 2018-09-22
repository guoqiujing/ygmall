package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.GoodsMapper;
import cn.myzqu.ygmall.pojo.Goods;
import cn.myzqu.ygmall.pojo.Spu;
import cn.myzqu.ygmall.service.GoodsService;
import cn.myzqu.ygmall.vo.GoodsDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Simon on 2018/9/19.
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public List<GoodsDetailVO> getGoodsAndSPU(String id) {
        List<GoodsDetailVO> goodsDetailVO=goodsMapper.getGoodsAndSPU(id);
        if(goodsDetailVO.size()>0){
            Spu spu=goodsDetailVO.get(0).getSpu();
            goodsDetailVO.get(0).setSpu(spu);
        }
        return goodsDetailVO;
    }
    public HashMap<String,String> getIdAndAttributes(String spuId){
        HashMap<String,String> hashMap=new HashMap<>();
        List<Goods> goodsList=goodsMapper.getIdAndAttributes(spuId);
        for(int i=0;i<goodsList.size();i++){
            hashMap.put(goodsList.get(i).getAttributes(),goodsList.get(i).getId());
        }
        return hashMap;
    }
}
