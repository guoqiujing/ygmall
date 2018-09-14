package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.BrandMapper;
import cn.myzqu.ygmall.pojo.Brand;
import cn.myzqu.ygmall.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 小奇冰 on 2018/9/12.
 */
@Service
public class BrandServiceImpl implements BrandService{

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAllBrand(){
        List<Brand> brandList = brandMapper.selectAllBrand();
        return brandList;
    }

    @Override
    public int addBrand(String brandname,Byte brandstatus){
        Brand brand=new Brand(brandname,brandstatus);
        int b=brandMapper.insertSelective(brand);
        return  b;
    }

    @Override
    public int deleteBrand(int id){
        int a=brandMapper.deleteByPrimaryKey(id);
        return a;
    }
}
