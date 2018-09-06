package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.pojo.Customer;
import cn.myzqu.ygmall.service.AccountService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Result register(String telephone, String email, String password){
        //调用注册顾客的服务层方法
        Customer customer = accountService.addCustomer(telephone, email, password);
        if(customer!=null){
            return ResultVOUtil.success(customer);
        }
        return ResultVOUtil.error("注册失败");
    }
}
