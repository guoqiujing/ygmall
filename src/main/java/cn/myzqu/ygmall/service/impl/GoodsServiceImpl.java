package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.GoodsMapper;
import cn.myzqu.ygmall.pojo.Goods;
import cn.myzqu.ygmall.pojo.Spu;
import cn.myzqu.ygmall.service.GoodsService;
import cn.myzqu.ygmall.utils.KeyUtil;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import cn.myzqu.ygmall.vo.GoodsDetailVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Simon on 2018/9/19.
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    /**
     * 创建新商品（商品上架时调用）
     */
    public Integer createNew(Goods goods){
        goods.setId(KeyUtil.getUUID());
        Integer integer=goodsMapper.insertSelective(goods);
        return integer;
    }
    @Override
    public List<GoodsDetailVO> getGoodsAndSPU(String id) {
        List<GoodsDetailVO> goodsDetailVO=goodsMapper.getGoodsAndSPU(id);
        if(goodsDetailVO.size()>0){
            Spu spu=goodsDetailVO.get(0).getSpu();
            goodsDetailVO.get(0).setSpu(spu);
        }
        return goodsDetailVO;
    }
    public BootstrapTableVO getAllPutOff(int pageSize, int pageIndex, String searchInput) {
        Page<Goods> page = PageHelper.startPage(pageIndex,pageSize);
        Map<String,Object> map = new HashMap<>();
        if(searchInput!=null&&searchInput!="") {
            map.put("name", searchInput);
        }
        List<Goods> goodsList=goodsMapper.getAllPutOff(map);
        int total = (int)page.getTotal();
        System.out.println("总记录数："+total);
        //把总记录数和某一页的记录装入BootstrapTableVO类
        BootstrapTableVO bto=new BootstrapTableVO(total,goodsList);
        if(total<=0)
            return null;
        return bto;
    }


    public HashMap<String,String> getIdAndAttributes(String spuId){
        HashMap<String,String> hashMap=new HashMap<>();
        List<Goods> goodsList=goodsMapper.getIdAndAttributes(spuId);
        for(int i=0;i<goodsList.size();i++){
            hashMap.put(goodsList.get(i).getAttributes(),goodsList.get(i).getId());
        }
        return hashMap;
    }
    public Integer putOffBySpuId(String spuId){
        Integer result=goodsMapper.putOffBySpuId(spuId);
        return result;
    }
    public Integer putOffById(String id){
        Integer result=goodsMapper.putOffById(id);
        return result;
    }

}
