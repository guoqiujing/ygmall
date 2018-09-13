package cn.myzqu.ygmall.controller;

/**
 * Created by Simon on 2018/9/13.
 */

import cn.myzqu.ygmall.pojo.Categories;
import cn.myzqu.ygmall.service.CategoriesService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
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
        return ResultVOUtil.error("查找类别失败");
    }
}
