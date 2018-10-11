package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.utils.VerificationUtil;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 短信发送控制器
 * Created by 小奇冰 on 2018/10/11.
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @PostMapping("/send")
    public Result sendMessage(String phone,HttpServletRequest request){
        HttpSession session = request.getSession(true);
        //调用短信api发送短信，获得验证码
        String code= VerificationUtil.send(phone);
        if(code.length()!=6){
            System.out.println("发生错误，错误码："+code);
            return ResultVOUtil.error("发生错误，错误码："+code);
        }
        else{
            System.out.println("发送成功");
            //生成session，将验证码和电话号码放入session，用作验证
            session.setAttribute("code",code);
            session.setAttribute("phone",phone);
            session.setMaxInactiveInterval(10*60);//以秒为单位，即在没有活动10分钟后，session将失效
            return  ResultVOUtil.success(code);
        }
    }
}
