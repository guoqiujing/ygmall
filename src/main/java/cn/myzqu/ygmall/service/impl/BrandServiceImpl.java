package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.BrandMapper;
import cn.myzqu.ygmall.pojo.Brand;
import cn.myzqu.ygmall.service.BrandService;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 品牌类服务接口实现类
 * Created by 小奇冰 on 2018/9/12.
 */
@Service
public class BrandServiceImpl implements BrandService{

    @Autowired
    private BrandMapper brandMapper;

    //查找全部品牌
    @Override
    public List<Brand> findAllBrand(){
        List<Brand> brandList = brandMapper.selectAllBrand();
        if(brandList.isEmpty()){
            return null;
        }
        return brandList;
    }

    @Override
    public BootstrapTableVO findBrand(Map<String, Object> map, Integer pageIndex, Integer pageSize){
        //分页插件
        Page<Brand> page = PageHelper.startPage(pageIndex,pageSize);
        List<Brand> brandList = brandMapper.selectBrand(map);
        //获取总记录数
        int total = (int)page.getTotal();
        System.out.println("总记录数："+total);
        //把总记录数和某一页的记录装入BootstrapTableVO类
        BootstrapTableVO bto=new BootstrapTableVO(total,brandList);
        if(total<=0)
            return null;

        return bto;
    }

    //添加品牌
    @Override
    public int addBrand(String brandName,Byte brandStatus){
        Brand brand=new Brand(brandName,brandStatus);
        int b=brandMapper.insertSelective(brand);
        return  b;
    }

    //单个删除
    @Override
    public int deleteBrand(int id){
        int a=brandMapper.deleteByPrimaryKey(id);
        return a;
    }

    //批量删除
    @Override
    public int deleteByIdList(List<Integer> idList){
        int c=brandMapper.deleteByIdList(idList);
        return c;
    }

    //修改品牌
    @Override
    public int modifyById(Brand brand) {
        int a=brandMapper.updateByPrimaryKey(brand);
        return a;
    }
}
