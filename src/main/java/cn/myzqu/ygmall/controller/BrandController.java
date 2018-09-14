package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.pojo.Brand;
import cn.myzqu.ygmall.pojo.Customer;
import cn.myzqu.ygmall.service.AccountService;
import cn.myzqu.ygmall.service.BrandService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 小奇冰 on 2018/9/12.
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @PostMapping("/list")
    public Result findAllBrand() {
        List<Brand> brand = brandService.findAllBrand();
        if (brand.size() > 0) {
            return ResultVOUtil.success(brand);
        }
        return ResultVOUtil.error("查找品牌失败");
    }

    @PostMapping("/add")
    public Result addBrand(String brandname,Byte brandstatus) {
        int b=brandService.addBrand(brandname,brandstatus);
        if(b>0){
            return  ResultVOUtil.success();
        }
        return  ResultVOUtil.error("添加品牌失败");
    }

    @PostMapping("/delete")
    public Result deleteBrand(int id){
        int a=brandService.deleteBrand(id);
        if(a>0){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("删除失败");

    }

}
