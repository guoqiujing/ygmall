package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.AfterSaleAlterMapper;
import cn.myzqu.ygmall.pojo.AfterSaleAlter;
import cn.myzqu.ygmall.service.AfterSaleAlterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by CC on 2018/10/16.
 */
@Service
public class AfterSaleAlterServiceImpl implements AfterSaleAlterService {
    @Autowired
    private AfterSaleAlterMapper afterSaleAlterMapper;

    @Override
    public int addAlter(AfterSaleAlter afterSaleAlter){
        int a=afterSaleAlterMapper.insert(afterSaleAlter);
        return a;
    }
}
