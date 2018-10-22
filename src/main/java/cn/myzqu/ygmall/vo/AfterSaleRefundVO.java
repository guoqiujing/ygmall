package cn.myzqu.ygmall.vo;

import cn.myzqu.ygmall.pojo.AfterSale;
import cn.myzqu.ygmall.pojo.Refund;


/**
 * Created by CC on 2018/10/22.
 */
public class AfterSaleRefundVO extends AfterSale {
    private Refund refund;

    public Refund getRefund() {
        return refund;
    }

    public void setRefund(Refund refund) {
        this.refund = refund;
    }
}
