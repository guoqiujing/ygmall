package cn.myzqu.ygmall.qcloud.cos;

/**
 * Created by 的川 on 2018/9/24.
 */
public  abstract  class Information {

    //开发者拥有的项目身份识别 ID，用以身份认证
    private   String SECRETID ;
    //开发者拥有的项目身份密钥
    private   String SECRETKEY ;
    //COS 中用于存储数据的容器
    private   String BUCKET ;
    //域名中的地域信息。
    private   String REGION ;


    public String getSECRETID() {
        return SECRETID;
    }

    public String getSECRETKEY() {
        return SECRETKEY;
    }

    public String getBUCKET() {
        return BUCKET;
    }


    public String getREGION() {
        return REGION;
    }


}
