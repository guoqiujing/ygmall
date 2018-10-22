package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.dto.PageDTO;
import cn.myzqu.ygmall.pojo.AfterSale;
import cn.myzqu.ygmall.vo.BootstrapTableVO;

import java.util.Map;

/**
 * Created by CC on 2018/10/15.
 */
public interface AfterSaleService {

    BootstrapTableVO findAfterSale(Map<String, Object> map, Integer pageIndex, Integer pageSize);

    AfterSale selectById(String id);

    int updateStatus(AfterSale afterSale);

    int addAfterSale(AfterSale afterSale);

    PageDTO selectAfterSale(String userId,Integer pageIndex,Integer pageSize);
}
