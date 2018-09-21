package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.SpuDetailMapper;
import cn.myzqu.ygmall.pojo.SpuDetail;
import cn.myzqu.ygmall.service.SpuDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Simon on 2018/9/20.
 */
@Service
public class SpuDetailServiceImpl implements SpuDetailService {
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Override
    public List<SpuDetail> selectBySPUId(String spuId) {
        List<SpuDetail> spuDetails=spuDetailMapper.selectBySPUId(spuId);
        return spuDetails;
    }
}
