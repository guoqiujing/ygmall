package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.exception.CustomException;
import cn.myzqu.ygmall.pojo.AfterSale;
import cn.myzqu.ygmall.service.AfterSaleService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import cn.myzqu.ygmall.vo.Result;
import com.github.pagehelper.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CC on 2018/10/15.
 */
@RestController
@RequestMapping("/afterSale")
@Slf4j
public class AfterSaleController {
    @Autowired
    private AfterSaleService afterSaleService;

    @PostMapping("/list")
    public Result findAfterSale(int pageSize,int pageIndex,Byte type,Byte status){
        System.out.println("每页数据条数："+pageSize);
        System.out.println("第几页："+pageIndex);
        System.out.println("要查询的售后类型："+type);
        System.out.println("要查询的状态："+status);
        //向Map中添加条件
        Map<String,Object> condition = new HashMap<>();
        condition.put("type",type);
        condition.put("status",status);

        BootstrapTableVO bto=afterSaleService.findAfterSale(condition,pageIndex,pageSize);
        if(bto!=null){
            return ResultVOUtil.success(bto);
        }

        return ResultVOUtil.error("查找售后失败");
    }

    /**
     * 根据售后id查询售后详情
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") String id){
        if(StringUtil.isEmpty(id)){
            log.error("【查询售后】id为空");
            throw new CustomException(1,"id为空");
        }
        AfterSale afterSale=afterSaleService.selectById(id);
        if(afterSale!=null){
            return ResultVOUtil.success(afterSale);
        }
        return ResultVOUtil.error("暂时没有售后");
    }

    /**
     * 卖家同意退款后修改售后状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/updateStatus")
    public Result updateStatus(String id,Byte status){
        AfterSale afterSale=new AfterSale(id,status);
        int a=afterSaleService.updateStatus(afterSale);
        if(a<=0){
            return ResultVOUtil.error("更改状态失败");
        }
        return ResultVOUtil.success();
    }
}