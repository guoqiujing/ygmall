package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.Brand;
import cn.myzqu.ygmall.vo.BootstrapTableVO;

import java.util.List;
import java.util.Map;

/**
 * Created by 小奇冰 on 2018/9/12.
 */
public interface BrandService {

    /**
     * 查找全部品牌
     * @return
     */
    List<Brand> findAllBrand();
    /**
     * 查找前num个品牌
     * @return
     */
    List<Brand> findTopBrand(Integer num);

    public Brand findBrandById(Integer id);
    /**
     * 查找并显示品牌记录
     * @return
     */
    BootstrapTableVO findBrand(Map<String, Object> map, Integer pageIndex, Integer pageSize);

    /**
     * 添加品牌
     * @param brandName
     * @param brandStatus
     * @return
     */
    int addBrand(String brandName , Byte brandStatus);

    /**
     * 删除品牌
     * @param id
     * @return
     */
    int deleteBrand(int id);

    /**
     * 批量删除品牌
     * @param idList
     * @return
     */
    int deleteByIdList(List<Integer> idList);

    /**
     * 根据id修改品牌
     */
    int modifyById(Brand brand);
}
