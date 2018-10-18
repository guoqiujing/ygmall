package cn.myzqu.ygmall.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

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

    public static List<T> ToList(String json ){

        return null;
    }


}
