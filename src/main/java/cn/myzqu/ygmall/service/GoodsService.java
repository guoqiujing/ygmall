package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.Goods;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import cn.myzqu.ygmall.vo.GoodsDetailVO;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Simon on 2018/9/19.
 */
public interface GoodsService {
    public Integer createNew(Goods goods);
    List<GoodsDetailVO> getGoodsAndSPU(String id);
    HashMap<String,String> getIdAndAttributes(String spuId);
    public Integer putOffBySpuId(String spuId);
    public Integer putOffById(String id);
    public BootstrapTableVO getAllPutOff(int pageSize, int pageIndex, String searchInput);
    public BootstrapTableVO getAllGoods_Spu_Img(int pageSize,int pageIndex,String searchInput);
    public Integer updateGoods(Goods goods);
}
