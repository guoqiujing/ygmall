package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.dto.UserSessionDTO;
import cn.myzqu.ygmall.pojo.Customer;
import cn.myzqu.ygmall.service.AccountService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 的川 on 2018/9/6.
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private AccountService accountService;
    @PostMapping("/login")
    public Result login(String code ,String password,HttpServletRequest request){
        /*
            正常来说这里判断code格式是手机号码还是邮箱
            然后根据不同的格式来调用不同的方法
         */
        HttpSession session = request.getSession(true);
        UserSessionDTO userSessionDTO=accountService.login(code,password);

        if(userSessionDTO!=null){
            //生成session
            session.setAttribute("user",userSessionDTO);
            Byte type=userSessionDTO.getType();
            if(type==1){
                return ResultVOUtil.success("user");
            }
            if(type==2){
                return ResultVOUtil.success("admin");
            }
        }
        return ResultVOUtil.error("登录失败");

    }

    @PostMapping("/logout")
    public Result logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return ResultVOUtil.success();
    }



}
