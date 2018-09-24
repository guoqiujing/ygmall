package cn.myzqu.ygmall.qcloud.cos;

import cn.myzqu.ygmall.utils.KeyUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by 的川 on 2018/9/24.
 */
public class Operate {


    /**
     * 上传单个文件到腾讯对象存储
     * @param file
     * @param information
     * @return
     */
    public static String upload(File file,Information information) {
        // 1 初始化
        // 1.1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(information.getSECRETID(), information.getSECRETKEY());
        // 1.2 设置bucket的区域
        ClientConfig clientConfig = new ClientConfig(new Region(information.getREGION()));
        // 1.3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // 2 上传文件
        //2.1 key必须保持唯一，重复会被重新
        String fileName = file.getName();
        System.out.println("上传的文件名为："+fileName);
        //获取原文件的后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称(唯一key，重复会被重写)
        String key = KeyUtil.getUUID() + suffix;
        PutObjectRequest putObjectRequest = new PutObjectRequest(information.getBUCKET(), key, file);
        cosclient.putObject(putObjectRequest);
        //3 关闭客户端
        cosclient.shutdown();

        //4 生成地址并返回:
        //https://ygmall-comment-1255574204.cos.ap-guangzhou.myqcloud.com/1.jpg
        StringBuffer url = new StringBuffer();
        url.append("https://").append(information.getBUCKET()).append(".cos.")
                .append(information.getREGION()).append(".myqcloud.com/")
                .append(key);
        return url.toString();
    }

}
