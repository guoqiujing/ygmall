package cn.myzqu.ygmall.qcloud.cos;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by 的川 on 2018/9/24.
 * 评论图片存储库
 */
@Getter
public class CommentInformation extends Information{
    //开发者拥有的项目身份识别 ID，用以身份认证
    private    String SECRETID = "AKIDscTGuTTTJCxXzFNZXIzUp8FD8uVv5D8w";
    //开发者拥有的项目身份密钥
    private    String SECRETKEY = "mU0KS3WmpgVQ9k6hBTYhFljYBbptqidN";
    //COS 中用于存储数据的容器
    private    String BUCKET = "ygmall-comment-1255574204";
    //域名中的地域信息。
    private    String REGION = "ap-guangzhou";

}
