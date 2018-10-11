package cn.myzqu.ygmall.utils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import java.io.IOException;

public class VerificationUtil {

    public static String send(String phone){
        // 短信应用SDK AppID
        int appid = 1400148837; // 1400开头

        // 短信应用SDK AppKey
        String appkey = "0d703bd96317d5d08fffaaf29ab3dc18";

        // 需要发送短信的手机号码
        String phoneNumbers = "21212313123";

        // 短信模板ID，需要在短信应用中申请
        int templateId = 208612; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
        //templateId7839对应的内容是"您的验证码是: {1}"

        String smsSign = "知晓砚园"; // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`

        String rancode="";//短信验证码
        try {
            //随机生成6位数字，作为短信验证码
            String code= String.valueOf((int)((Math.random()*9+1)*100000));
            // String code= String.valueOf(((Math.random()*9+1)*100000));
            rancode=code;
            String[] params = {code};//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phone,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.println(result);
            if(result.result!=0){
                return String.valueOf(result.result);//发生错误时，返回错误码
            }
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return rancode;
    }


//    public static void main(String[] args) {
//        VerificationUtil vu=new VerificationUtil();
//        String code=vu.send("18820842091");
//        if(code.length()!=6){
//            System.out.println("发生错误，错误码："+code);
//        }
//        else{
//            System.out.println("发送成功，验证码："+code);
//        }
//    }
}
