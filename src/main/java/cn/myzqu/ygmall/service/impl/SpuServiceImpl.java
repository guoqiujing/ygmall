package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.SpuMapper;
import cn.myzqu.ygmall.pojo.Spu;
import cn.myzqu.ygmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Simon on 2018/9/19.
 */
@Service
public class SpuServiceImpl implements SpuService{
    @Autowired
    private SpuMapper spuMapper;
    @Override
    public Spu selectByPrimaryKey(String id) {
        Spu spu=spuMapper.selectByPrimaryKey(id);
        return spu;
    }
}
