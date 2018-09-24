package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.dto.UserDTO;
import cn.myzqu.ygmall.dto.UserSessionDTO;
import cn.myzqu.ygmall.enums.ResultEnum;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 小奇冰 on 2018/9/23.
 */
@RestController
@RequestMapping("session")
public class SessionController {

    @RequestMapping("/user")
    public Result getSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        UserSessionDTO userSessionDTO = (UserSessionDTO) session.getAttribute("user");
        if(userSessionDTO!=null){
            System.out.println("当前请求用户："+userSessionDTO);
            return ResultVOUtil.success(userSessionDTO);
        }
        return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);

    }
}
