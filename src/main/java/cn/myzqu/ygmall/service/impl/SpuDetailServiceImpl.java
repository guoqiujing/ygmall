package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.SpuDetailMapper;
import cn.myzqu.ygmall.pojo.SpuDetail;
import cn.myzqu.ygmall.service.SpuDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Simon on 2018/9/20.
 */
@Service
public class SpuDetailServiceImpl implements SpuDetailService {
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Override
    public List<SpuDetail> selectBySPUId(String spuId) {
        List<SpuDetail> spuDetails=spuDetailMapper.selectBySPUId(spuId);
        return spuDetails;
    }
    public Integer insert(String spuId,String urlList) {
        String[] urls=urlList.split(",");
        int i;
        for(i=0;i<urls.length;i++){
            SpuDetail spuDetail=new SpuDetail();
            spuDetail.setImgOrder(i);
            spuDetail.setImgUrl(urls[i]);
            spuDetail.setSpuId(spuId);
            if(spuDetailMapper.insert(spuDetail)!=1)
                break;
        }
        if(i!=urls.length)
            return null;
        return 1;
    }
    public Integer delete(String spuId) {
        Integer result=spuDetailMapper.deleteBySpuId(spuId);
        return result;
    }
    public Integer update(String spuId,String urlList) {
        Integer result=0;
        Integer delResult=1;
        List<SpuDetail> spuDetails=selectBySPUId(spuId);
        if(spuDetails.size()>0)
            delResult=delete(spuId);
        if(delResult>0)
            result=insert(spuId,urlList);
        return result;
    }
}
