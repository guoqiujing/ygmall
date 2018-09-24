package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.pojo.SpuDetail;
import cn.myzqu.ygmall.service.SpuDetailService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Simon on 2018/9/20.
 */
@RestController
@RequestMapping("/spuDetail")
public class SpuDetailController {
    @Autowired
    private SpuDetailService spuDetailService;

    @PostMapping("/getSpuDetailBySPUId")
    public Result getSpuDetailBySPUId(String spuId){
        List<SpuDetail> spuDetails=spuDetailService.selectBySPUId(spuId);
        if(spuDetails.size()>0){
            return ResultVOUtil.success(spuDetails);
        }
        return ResultVOUtil.error("查找商品详情图片失败");
    }
}