package cn.myzqu.ygmall.controller;

/**
 * Created by Simon on 2018/9/13.
 */

import cn.myzqu.ygmall.pojo.Categories;
import cn.myzqu.ygmall.service.CategoriesService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.CategoriesVO;
import cn.myzqu.ygmall.vo.GoodsCategoriesVO;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;
    @PostMapping("/findAll")
    public Result findAllCategories() {
        List<Categories> categories = categoriesService.findAllCategories();
        if (categories.size() > 0) {
            return ResultVOUtil.success(categories);
        }
        return ResultVOUtil.error("查找所有类别失败");
    }
    @PostMapping("/GetCategories_l1")
    public Result getCategories_l1() {
        List<Categories> categories = categoriesService.findAllL1Categories();
        if (categories.size() > 0) {
            return ResultVOUtil.success(categories);
        }
        return ResultVOUtil.error("查找一级类别失败");
    }
    @PostMapping("/GetCategories_l23")
    public Result getCategories_l23(Integer id) {
        List<Categories> categories = categoriesService.getL23CategoriesById(id);
        if (categories.size() > 0) {
            return ResultVOUtil.success(categories);
        }
        return ResultVOUtil.error("查找子类别失败");
    }
    @PostMapping("/updateCategories")
    public Result updateCategories(Integer id,String name,Byte status) {
        Categories categories=new Categories();
        categories.setId(id);
        categories.setName(name);
        categories.setStatus(status);
        Integer result=categoriesService.updateCategories(categories);
        if (result==1) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("修改类别失败");
    }
    @PostMapping("/deleteCategories")
    public Result deleteCategories(Integer id) {
        Integer result=categoriesService.deleteCategories(id);
        if (result==1) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("删除类别失败");
    }
    @PostMapping("/insertCategories")
    public Result insertCategories(Categories categories) {
        Integer result=categoriesService.insertCategories(categories);
        if (result==1) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("插入类别失败");
    }
    @PostMapping("/getCompleteCategoriesById")
    public Result getCompleteCategoriesById(Integer id) {
        GoodsCategoriesVO goodsCategoriesVO=categoriesService.getCompleteCategoriesById(id);
        if (goodsCategoriesVO!=null) {
            return ResultVOUtil.success(goodsCategoriesVO);
        }
        return ResultVOUtil.error("查找子类别失败");
    }
    @PostMapping("/getAllCompleteCategories")
    public Result getAllCompleteCategories() {
        CategoriesVO categoriesVO=categoriesService.getAllCompleteCategories();
        if (categoriesVO!=null) {
            return ResultVOUtil.success(categoriesVO);
        }
        return ResultVOUtil.error("查找类别失败");
    }
}
