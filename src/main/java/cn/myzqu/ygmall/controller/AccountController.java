package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.dto.UserAndPayUserDTO;
import cn.myzqu.ygmall.pojo.Customer;
import cn.myzqu.ygmall.service.AccountService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 的川 on 2018/9/6.
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    @ResponseBody
    public Result register(String code, String telephone, String email, String password, HttpServletRequest request){
        HttpSession session = request.getSession();
        //取出存在session的验证码和手机号
        String sessionCode=(String)session.getAttribute("code");
        String sessionPhone=(String)session.getAttribute("phone");
        if(!telephone.equals(sessionPhone)){
            return ResultVOUtil.error("手机号不匹配，请重新发送验证码");
        }
        else if(!code.equals(sessionCode)){
            return ResultVOUtil.error("验证码错误");
        }
        //调用注册顾客的服务层方法
        Customer customer = accountService.addCustomer(telephone, email, password);
        if(customer!=null){
            return ResultVOUtil.success(customer);
        }
        return ResultVOUtil.error("注册失败");
    }

    /**
     * 修改密码
     * @param id
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @PostMapping("/updatePwd")
    @ResponseBody
    public Result updatePwd(String id, String oldPwd, String newPwd){
        int re=accountService.updatePassword(id,oldPwd,newPwd);
        if(re!=0){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("修改密码失败");
    }

    /**
     * 查找用户数和付费用户数
     * @return
     */
    @PostMapping("/findUserAndPay")
    @ResponseBody
    public Result findUserAndPay(){
        UserAndPayUserDTO upo=accountService.findUserAndPayUser();
        if(upo!=null){
            return ResultVOUtil.success(upo);
        }
        return ResultVOUtil.error("查找失败");
    }

}
