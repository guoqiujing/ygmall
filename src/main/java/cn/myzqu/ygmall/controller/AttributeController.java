package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.pojo.Attribute;
import cn.myzqu.ygmall.pojo.GoodsImg;
import cn.myzqu.ygmall.service.AttributeService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.CategoriesAttributeVO;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Simon on 2018/9/21.
 */
@RestController
@RequestMapping("/attribute")
public class AttributeController {
    @Autowired
    private AttributeService attributeService;
    @PostMapping("/getCategoriesAttributeByCategoryId")  //√
    public Result getCategoriesAttributeByCategoryId(Integer categoryId){
        CategoriesAttributeVO categoriesAttributeVO=attributeService.getCategoriesAttributeByCategoryId(categoryId);
        if (categoriesAttributeVO!=null) {
            return ResultVOUtil.success(categoriesAttributeVO);
        }
        return ResultVOUtil.error("查找类别规格信息失败");
    }
    @PostMapping("/getAllCategoriesAttribute")  //√
    public Result getAllCategoriesAttribute(){
        List<CategoriesAttributeVO> categoriesAttributeVOs=attributeService.getAllCategoriesAttribute();
        if (categoriesAttributeVOs.size()>0) {
            return ResultVOUtil.success(categoriesAttributeVOs);
        }
        return ResultVOUtil.error("查找所有类别规格失败");
    }
    @PostMapping("/deleteAttributeByCategoryId")  //√
    public Result deleteAttributeByCategoryId(Integer categoryId){
        Integer result=attributeService.deleteByCategoryId(categoryId);
        if (result!=0) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("删除类别规格失败");
    }
    @PostMapping("/insertAttribute")  //√
    public Result insertAttribute(Attribute attribute){
        Integer result=attributeService.insertAttribute(attribute);
        if (result==1) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("添加类别规格失败");
    }
}
