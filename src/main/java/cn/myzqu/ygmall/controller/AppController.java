package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 的川 on 2018/9/6.
 */
@RestController
@RequestMapping("/app")
public class AppController {
    @GetMapping("/hello")
    public Result hello(){
        return ResultVOUtil.success();
    }



}
