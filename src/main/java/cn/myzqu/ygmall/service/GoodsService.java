package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.dto.PageDTO;
import cn.myzqu.ygmall.pojo.Goods;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import cn.myzqu.ygmall.vo.GoodsDetailVO;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Simon on 2018/9/19.
 */
public interface GoodsService {
    Integer createNew(Goods goods);
    List<GoodsDetailVO> getGoodsAndSPU(String id);
    HashMap<String,String> getIdAndAttributes(String spuId);
    Integer putOffBySpuId(String spuId);
    Integer putOffById(String id);
    BootstrapTableVO getAllPutOff(int pageSize, int pageIndex, String searchInput);
    BootstrapTableVO getAllGoods_Spu_Img(int pageSize,int pageIndex,String searchInput);
    Integer updateGoods(Goods goods);

    /**
     * 搜索商品
     * @param search
     * @param pageSize
     * @param pageIndex
     * @return
     */
    PageDTO searchGoods(String search, int pageSize, int pageIndex);

    PageDTO getSimpleGoods(String GoodsIdString);
}
