package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.pojo.Customer;
import cn.myzqu.ygmall.service.CustomerService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 用户信息控制层
 * Created by 小奇冰 on 2018/9/19.
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /**
     * 根据条件分页查找用户信息
     * @return
     */
    @PostMapping("/list")
    public Result findCustomer(int pageSize,int pageIndex,String nickName,Byte sex) {
        System.out.println("每页数据条数："+pageSize);
        System.out.println("第几页："+pageIndex);
        System.out.println("要查询的昵称："+nickName);
        System.out.println("要查询的性别："+sex);
        //向Map中添加条件
        Map<String,Object> condition = new HashMap<>();
        condition.put("nickName",nickName);
        condition.put("sex",sex);

        BootstrapTableVO bto= customerService.findCustomer(condition,pageIndex,pageSize);
        if(bto!=null){
            return ResultVOUtil.success(bto);
        }
        return ResultVOUtil.error("查找用户信息失败");
    }

    /**
     * 根据id查找用户
     * @param id 用户id
     * @return
     */
    @PostMapping("/info")
    public Result getCustomerById(String id){
        Customer customer=customerService.findCustomerById(id);
        if(customer!=null){
            return ResultVOUtil.success(customer);
        }
        return  ResultVOUtil.error("找不到该用户");
    }

    /**
     * 修改用户信息
     * @param id
     * @param nickname
     * @param icon
     * @param sex
     * @param telephone
     * @param email
     * @return
     */
    @PostMapping("/update")
    public Result updateCustomerById(String id,String nickname,String icon,Byte sex,String telephone,String email){
        Customer customer=new Customer(id,nickname,icon,sex,telephone,email);
        int c=customerService.updateCustomerById(customer);
        if(c<=0){
            return  ResultVOUtil.error("修改失败");
        }
        return  ResultVOUtil.success();
    }
}
