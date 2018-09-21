package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.pojo.Spu;
import cn.myzqu.ygmall.pojo.SpuDetail;

import java.util.List;

/**
 * Created by Simon on 2018/9/19.
 */
public interface SpuService {
    Spu selectByPrimaryKey(String id);
}
