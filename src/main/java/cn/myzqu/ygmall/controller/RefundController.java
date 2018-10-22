package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.pojo.Refund;
import cn.myzqu.ygmall.service.RefundService;
import cn.myzqu.ygmall.utils.KeyUtil;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CC on 2018/10/16.
 */
@RestController
@RequestMapping("/refund")
public class RefundController {
    @Autowired
    private RefundService refundService;

    /**
     * 添加退款记录
     * @param step
     * @param operator
     * @return
     */
    @PostMapping("/addRefund")
    public Result addRefund(String serial,int step,String operator){
        String id= KeyUtil.getUUID();
        Refund refund=new Refund(id,serial,step,operator);
        int r=refundService.addRefund(refund);
        if(r<=0){
            return ResultVOUtil.error("添加退款失败");
        }
        return ResultVOUtil.success();
    }

    /**
     * 查询退款记录分页显示
     * @param pageSize
     * @param pageIndex
     * @param step
     * @return
     */
    @PostMapping("/list")
    public Result findRefund(int pageSize,int pageIndex,Integer step){
        System.out.println("每页数据条数："+pageSize);
        System.out.println("第几页："+pageIndex);
        System.out.println("要查询的退款状态："+step);

        //向Map中添加条件
        Map<String,Object> condition = new HashMap<>();
        condition.put("step",step);

        BootstrapTableVO bto=refundService.findRefund(condition,pageIndex,pageSize);
        if(bto!=null){
            return ResultVOUtil.success(bto);
        }
        return ResultVOUtil.error("查找退款记录失败");
    }

    @PostMapping("/updateStep")
    public Result updateStep(String id,Integer step){
        Refund refund=new Refund(id,step);
        int r=refundService.updateStep(refund);
        if(r<=0){
            return ResultVOUtil.error("更改退款状态失败");
        }
        return ResultVOUtil.success();
    }
}
