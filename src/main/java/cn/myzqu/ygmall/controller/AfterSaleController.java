package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.dto.PageDTO;
import cn.myzqu.ygmall.exception.CustomException;
import cn.myzqu.ygmall.pojo.AfterSale;
import cn.myzqu.ygmall.pojo.AfterSaleAlter;
import cn.myzqu.ygmall.service.AfterSaleAlterService;
import cn.myzqu.ygmall.service.AfterSaleService;
import cn.myzqu.ygmall.utils.KeyUtil;
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

    @Autowired
    private AfterSaleAlterService afterSaleAlterService;

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

    /**
     * 买家取消申请售后，修改售后表状态并向售后变更状态表插入数据
     * @param id
     * @return
     */
    @PostMapping("/modifyStatus")
    public Result modifyStatus(String id){
        Byte status=3;
        String id2=KeyUtil.getUUID();
        AfterSale afterSale=new AfterSale(id,status);
        AfterSaleAlter afterSaleAlter=new AfterSaleAlter(id2,id,status);
        int a=afterSaleService.updateStatus(afterSale);
        int b=afterSaleAlterService.addAlter(afterSaleAlter);
        if(a>0&&b>0){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("取消申请售后失败");
    }

    /**
     * 添加售后数据
     * @param orderId
     * @param type
     * @param reason
     * @param description
     * @param receiverName
     * @param receiverTel
     * @return
     */
    @PostMapping("/addAfterSale")
    public Result addAfterSale(String orderId,Byte type,String reason,String description,String receiverName,String receiverTel){
        String id= KeyUtil.getUUID();
        String id2=KeyUtil.getUUID();
        Byte state=2;
        AfterSale afterSale=new AfterSale(id,orderId,type,reason,description,receiverName,receiverTel);
        AfterSaleAlter afterSaleAlter=new AfterSaleAlter(id2,id,state);
        int a=afterSaleService.addAfterSale(afterSale);
        int b=afterSaleAlterService.addAlter(afterSaleAlter);
        if(a>0&&b>0){
            return ResultVOUtil.success();

        }
        return ResultVOUtil.error("添加申请售后失败");
    }

    /**
     * 根据用户id获取售后列表
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/getAfterSale")
    public Result getAfterSale(String userId, Integer page, Integer size){
        System.out.println(userId);
        PageDTO pageDTO=afterSaleService.selectAfterSale(userId,page,size);
        if (pageDTO != null) {
            return ResultVOUtil.success(pageDTO);
        }
        return ResultVOUtil.error("暂时没有数据哦！");
    }
}

