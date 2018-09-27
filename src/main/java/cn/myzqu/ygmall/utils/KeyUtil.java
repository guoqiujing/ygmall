package cn.myzqu.ygmall.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 生成主码工具类
 * Created by 的川 on 2018/5/8.
 */
public class KeyUtil {

    /**
     * 生成指定位数的随机码（阿拉伯数字+小写英文字母）
     * @param digit
     * @return
     */
    public static String getRandomCode(int digit){
        String result = "";
        String sample = "0123456789abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < digit ; i++)
        {
            int rand = (int) (Math.random() * sample.length());
            result = result + sample.charAt(rand);
        }
        return result;
    }

    /**
     * 生成密码加密秘钥
     * @return
     */
    public  static String getSalt(){
        return getRandomCode(6);
    }

    /**
     * 生成uuid
     * @return
     */
    public static String getUUID(){
        return  UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }

}
