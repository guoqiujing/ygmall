package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.Spu;
import cn.myzqu.ygmall.pojo.SpuDetail;
import cn.myzqu.ygmall.vo.BootstrapTableVO;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Simon on 2018/9/19.
 */
public interface SpuService {
    Spu selectByPrimaryKey(String id);
    public int insert(String spuId,String name,Integer categoryId,Integer brandId,String[] attrNamesArray,String[] attrValuesArray,String subtitle) throws ParseException;
    public BootstrapTableVO selectIdAndName(int pageSize, int pageIndex, String searchInput);
}
