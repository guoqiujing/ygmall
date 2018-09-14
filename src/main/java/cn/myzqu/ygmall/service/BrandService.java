package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.Brand;

import java.util.List;

/**
 * Created by 小奇冰 on 2018/9/12.
 */
public interface BrandService {
    /**
     * 查找并显示所有品牌记录
     * @return
     */
    List<Brand> findAllBrand();

    int addBrand(String brandname , Byte brandstatus);

    int deleteBrand(int id);
}
