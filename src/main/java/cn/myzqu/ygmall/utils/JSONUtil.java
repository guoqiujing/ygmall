package cn.myzqu.ygmall.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by 的川 on 2018/8/11.
 */
public class JSONUtil {

    public static <T> T ObjectToJavaClass(Object object,Class<T> classType){
        String  json= JSONObject.toJSONString(object);
        System.out.println(json);
        T t= JSONObject.parseObject(json,classType);
        return t;
    }

//    public static List<T> T ToList(List<Object> object, Class<E> classType){
//        String  json= JSONObject.toJSONString(object);
//        System.out.println(json);
//        T t = JSONObject.parseArray(json,classType);
//        return t;
//    }
}
