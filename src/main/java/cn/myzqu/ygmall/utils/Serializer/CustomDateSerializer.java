package cn.myzqu.ygmall.utils.Serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定时间转换器
 * Created by 的川 on 2018/5/8.
 */
public class CustomDateSerializer extends JsonSerializer<Date>{

    /**
     * 重写时间序列化方法，序列化格式为"yyyy-MM-dd HH:mm"
     * @param value
     * @param jgen
     * @param provider
     * @throws IOException
     * @throws JsonProcessingException
     */
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = formatter.format(value);
        jgen.writeString(formattedDate);
    }

}
