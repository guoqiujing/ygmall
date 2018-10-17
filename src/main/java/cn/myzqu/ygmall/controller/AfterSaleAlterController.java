package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.pojo.AfterSaleAlter;
import cn.myzqu.ygmall.service.AfterSaleAlterService;
import cn.myzqu.ygmall.utils.KeyUtil;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by CC on 2018/10/16.
 */
@RestController
@RequestMapping("/afterSaleAlter")
public class AfterSaleAlterController {
    @Autowired
    private AfterSaleAlterService afterSaleAlterService;

    @PostMapping("/addAlter")
    public Result addAlter(String afterSaleId,Byte state,String operator){
        String id= KeyUtil.getUUID();
        AfterSaleAlter afterSaleAlter=new AfterSaleAlter(id,afterSaleId,state,operator);
        int a=afterSaleAlterService.addAlter(afterSaleAlter);
        if(a<=0){
            return ResultVOUtil.error("添加变更状态失败");
        }
        return  ResultVOUtil.success();
    }
}
