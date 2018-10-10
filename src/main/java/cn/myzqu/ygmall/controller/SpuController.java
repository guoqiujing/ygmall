package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.pojo.Spu;
import cn.myzqu.ygmall.pojo.SpuDetail;
import cn.myzqu.ygmall.service.SpuService;
import cn.myzqu.ygmall.utils.KeyUtil;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import cn.myzqu.ygmall.vo.Result;
import cn.myzqu.ygmall.vo.SpuDetailVO;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String spuId= KeyUtil.getUUID();
        Integer result=spuService.insert(spuId,name,categoryId,brandId,attrNamesArray,attrValuesArray,subtitle);
       if(result==1){
            return ResultVOUtil.success(spuId);
        }
        return ResultVOUtil.error("新增货品信息失败");
    }
    @PostMapping("/updateSPUSelective")
    public Result updateSPUSelective(String id, String name, String subtitle, Integer saleCount, Integer commentCount,String attrNamesArray[], String attrValuesArray[]) throws ParseException {
        Integer result=spuService.updateSPUSelective(id,name,subtitle,saleCount,commentCount,attrNamesArray,attrValuesArray);
        if(result==1){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("修改货品信息失败");
    }
    @PostMapping("/updateSPU")
    public Result updateSPU(Spu spu){
        Integer result=spuService.updateByPrimaryKeySelective(spu);
        if(result==1){
            return ResultVOUtil.success(result);
        }
        return ResultVOUtil.error("修改货品信息失败");
    }
    @PostMapping("/updateSPU_Attr")
    public Result updateSPU_Attr(String oSpuId,String FName0,String oFVal0,String nFval0,String FName1,String oFVal1,String nFval1,String FName2,String oFVal2,String nFval2){
        Integer result=spuService.updateSPU_Attr( oSpuId, FName0, oFVal0, nFval0, FName1, oFVal1, nFval1, FName2, oFVal2, nFval2);
        if(result==1){
            return ResultVOUtil.success(result);
        }
        return ResultVOUtil.error("修改货品信息的属性名字符串失败");
    }

    /**
     * 获取所有未下架的货品的id、name、createtime、category_id
     * @param pageSize
     * @param pageIndex
     * @param searchInput
     * @return
     */
    @PostMapping("/findAllIdAndName")
    public Result selectAllIdAndName(int pageSize,int pageIndex,String searchInput){
        BootstrapTableVO bto=spuService.selectIdAndName(pageSize,pageIndex,searchInput);
        if(bto!=null){
            return ResultVOUtil.success(bto);
        }
        return ResultVOUtil.error("获取所有货品id、name失败");
    }
    @PostMapping("/putOff")
    public Result putOffById(String id){
        Integer result=spuService.putOff(id);
        if(result==1){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("下架货品失败");
    }

    /**
     * 修改货品状态为“上架”（默认状态）
     * @param id
     * @return
     */
    @PostMapping("/putOn")
    public Result putOnById(String id){
        Integer result=spuService.putOn(id);
        if(result==1){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("修改货品状态为“上架”失败");
    }

    /**
     * 查找所有货品的详细信息（包括图片、类别路径等）
     * @param pageSize
     * @param pageIndex
     * @param searchInput
     * @return
     */
    @PostMapping("/selectAllSpu_Img")
    public Result selectAllSpu_Img(int pageSize,int pageIndex,String searchInput){
        BootstrapTableVO bto=spuService.selectAllSpu_Img(pageSize,pageIndex,searchInput);
        if(bto!=null){
            return ResultVOUtil.success(bto);
        }
        return ResultVOUtil.error("获取所有货品信息及其图片信息失败");
    }

    /**
     * 查找某个货品的详细信息（包括图片、类别路径等）
     * @param id
     * @return
     */
    @PostMapping("/selectSpu_ImgById")
    public Result selectSpu_ImgById(String id){
        SpuDetailVO spuDetailVO=spuService.selectSpu_ImgById(id);
        if(spuDetailVO!=null){
            return ResultVOUtil.success(spuDetailVO);
        }
        return ResultVOUtil.error("获取所有货品信息及其图片信息失败");
    }

}
