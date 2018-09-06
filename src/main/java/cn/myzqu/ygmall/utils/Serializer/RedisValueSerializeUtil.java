package cn.myzqu.ygmall.utils.Serializer;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;


public class RedisValueSerializeUtil implements RedisSerializer<Object> {

    static final byte[] EMPTY_ARRAY = new byte[0];
    private final Charset charset;

    public RedisValueSerializeUtil() {
        // TODO Auto-generated constructor stub
        this(Charset.forName("UTF8"));
    }

    public RedisValueSerializeUtil(Charset charset) {
        // TODO Auto-generated constructor stub
        this.charset = charset;
    }

    @Override
    public byte[] serialize(Object object){  //序列化方法
        // TODO Auto-generated method stub
        try {
            String jsonString = JSONObject.toJSONString(object);
            return (jsonString == null ? EMPTY_ARRAY : jsonString.getBytes(charset));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException { //反序列化
        // TODO Auto-generated method stub
        String objectStr = null;
        Object object = null;
        if (bytes == null) {
            return object;
        }
        try {
            objectStr = new String(bytes,charset); //byte数组转换为String
            object = JSONObject.parse(objectStr);  //返回的是JSONObject类型，取数据时候需要再次转换一下
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return object;
    }

}
