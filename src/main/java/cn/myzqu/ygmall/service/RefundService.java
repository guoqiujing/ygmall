package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.Refund;
import cn.myzqu.ygmall.vo.BootstrapTableVO;

import java.util.Map;

/**
 * Created by CC on 2018/10/16.
 */
public interface RefundService {
    int addRefund(Refund refund);

    BootstrapTableVO findRefund(Map<String, Object> map, Integer pageIndex, Integer pageSize);

    int updateStep(Refund refund);
}
