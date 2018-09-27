package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.SpuDetail;

import java.util.List;

/**
 * Created by Simon on 2018/9/20.
 */
public interface SpuDetailService {
    List<SpuDetail> selectBySPUId(String spuId);
    public Integer insert(String spuId,String urlList);
}
