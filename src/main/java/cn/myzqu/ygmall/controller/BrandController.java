package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.pojo.Brand;
import cn.myzqu.ygmall.service.BrandService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 品牌信息控制层
 * Created by 小奇冰 on 2018/9/12.
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 查找全部品牌
     * @return
     */
    @GetMapping("/findAll")
    public Result findAllBrand(){
        List<Brand> brandList=brandService.findAllBrand();
        if(brandList==null){
            return ResultVOUtil.error("找不到品牌记录");
        }
        return ResultVOUtil.success(brandList);
    }

    /**
     * 根据条件分页查找品牌
     * @return
     */
    @PostMapping("/list")
    public Result findBrand(int pageSize,int pageIndex,String brandName,Byte brandStatus) {
        System.out.println("每页数据条数："+pageSize);
        System.out.println("第几页："+pageIndex);
        System.out.println("要查询的品牌名："+brandName);
        System.out.println("要查询的状态："+brandStatus);
        //向Map中添加条件
        Map<String,Object> condition = new HashMap<>();
        condition.put("brandName",brandName);
        condition.put("brandStatus",brandStatus);

        BootstrapTableVO bto= brandService.findBrand(condition,pageIndex,pageSize);
        if(bto!=null){
           return ResultVOUtil.success(bto);
        }
        return ResultVOUtil.error("查找品牌失败");
    }

    /**
     * 添加品牌
     * @param brandName
     * @param brandStatus
     * @return
     */
    @PostMapping("/add")
    public Result addBrand(String brandName,Byte brandStatus) {
        int b=brandService.addBrand(brandName,brandStatus);
        if(b>0){
            return  ResultVOUtil.success();
        }
        return  ResultVOUtil.error("添加品牌失败");
    }

    /**
     * 删除品牌
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Result deleteBrand(int id) {
        int a=brandService.deleteBrand(id);
        if(a>0){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("删除失败");

    }

    /**
     * 批量删除品牌
     * @param idList
     * @return
     */
    @PostMapping("/batchDelete")
    @ResponseBody
    public Result deleteByIdList(Integer idList[]) {
        List<Integer> resultList= new ArrayList<>(Arrays.asList(idList));
        int a=brandService.deleteByIdList(resultList);
                if(a>0){
                    return  ResultVOUtil.success();
                }
        return ResultVOUtil.error("批量删除失败");
    }

    /**
     * 根据id修改品牌
     * @param brandId
     * @param brandName
     * @param brandStatus
     * @return
     */
    @PostMapping("/modify")
    public Result modifyById(Integer brandId, String brandName,Byte brandStatus) {
        Brand brand=new Brand(brandId,brandName,brandStatus);
        int b=brandService.modifyById(brand);
        if(b>0){
            return  ResultVOUtil.success();
        }
        return  ResultVOUtil.error("修改品牌失败");
    }
}
