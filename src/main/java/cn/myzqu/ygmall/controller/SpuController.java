package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.service.SpuService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.Result;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * Created by Simon on 2018/9/25.
 */
@RestController
@RequestMapping("/spu")
public class SpuController {
    @Autowired
    private SpuService spuService;
    @PostMapping("/createSPU")
    public Result createSPU(String name, Integer categoryId, Integer brandId, String attrNamesArray[], String attrValuesArray[], String subtitle) throws ParseException {
        Integer result=spuService.insert(name,categoryId,brandId,attrNamesArray,attrValuesArray,subtitle);
        if(result==1){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("新增货品信息失败");
    }
}
