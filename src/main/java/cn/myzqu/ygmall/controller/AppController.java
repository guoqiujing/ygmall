package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.pojo.Customer;
import cn.myzqu.ygmall.service.AccountService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 的川 on 2018/9/6.
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private AccountService accountService;
    @PostMapping("/login")
    public Result login(String code ,String password){
        /*
            正常来说这里判断code格式是手机号码还是邮箱
            然后根据不同的格式来调用不同的方法
         */
        Customer customer=accountService.login(code,password);
        if(customer!=null){
            return ResultVOUtil.success(customer);
        }
        return ResultVOUtil.error("登录失败");

    }



}
