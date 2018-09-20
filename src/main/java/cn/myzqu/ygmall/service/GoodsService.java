package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.vo.GoodsDetailVO;

import java.util.List;

/**
 * Created by Simon on 2018/9/19.
 */
public interface GoodsService {
    List<GoodsDetailVO> getGoodsAndSPU(String id);
}
