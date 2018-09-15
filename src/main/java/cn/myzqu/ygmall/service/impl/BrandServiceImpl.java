package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.BrandMapper;
import cn.myzqu.ygmall.pojo.Brand;
import cn.myzqu.ygmall.service.BrandService;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 品牌类服务接口实现类
 * Created by 小奇冰 on 2018/9/12.
 */
@Service
public class BrandServiceImpl implements BrandService{

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public BootstrapTableVO findAllBrand(int pageIndex,int pageSize){
        //分页插件
        Page<Brand> page = PageHelper.startPage(pageIndex,pageSize);
        List<Brand> brandList = brandMapper.selectAllBrand();
        //获取总记录数
        int total = (int)page.getTotal();
        System.out.println("总记录数："+total);

        //把总记录数和某一页的记录装入BootstrapTableVO类
        List<Brand> b=new ArrayList<>();

//        for(int i=0;i<pageSize;i++)
//            b.add(page.getResult().get(i));

        System.out.println(b);
        BootstrapTableVO bto=new BootstrapTableVO(total,brandList);
        System.out.println(bto.toString());
        if(total<=0) return null;

        return bto;
    }

    @Override
    public int addBrand(String brandName,Byte brandStatus){
        Brand brand=new Brand(brandName,brandStatus);
        int b=brandMapper.insertSelective(brand);
        return  b;
    }

    @Override
    public int deleteBrand(int id){
        int a=brandMapper.deleteByPrimaryKey(id);
        return a;
    }

    @Override
    public int deleteByIdList(List<Integer> idList){
        int c=brandMapper.deleteByIdList(idList);
        return c;
    }

    @Override
    public int modifyById(Brand brand) {
        int a=brandMapper.updateByPrimaryKey(brand);
        return a;
    }
}
