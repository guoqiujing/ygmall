package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.pojo.CustomerAddress;
import cn.myzqu.ygmall.service.CustomerAddressService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 小奇冰 on 2018/9/21.
 */
@RestController
@RequestMapping("/customerAddress")
public class CustomerAddressController {
    @Autowired
    private CustomerAddressService customerAddressService;

    /**
     * 根据id查找收货地址
     * @param id
     * @return
     */
    @PostMapping("/findById")
    public Result findById(String id){
        CustomerAddress customerAddress=customerAddressService.findById(id);
        if(customerAddress!=null){
            return ResultVOUtil.success(customerAddress);
        }
        return ResultVOUtil.error("找不到该收货地址");
    }

    /**
     * 根据用户id查找收货地址
     * @param userId
     * @return
     */
    @PostMapping("/findByUserId")
    public Result findCustomerAddressByUserId(String userId){
        List<CustomerAddress> customerAddressList=customerAddressService.findByUserId(userId);
        if(customerAddressList!=null){
            return ResultVOUtil.success(customerAddressList);
        }
        return ResultVOUtil.error("找不到该用户的收货地址");
    }

    /**
     * 增加收货地址
     * @param userId
     * @param receiverName
     * @param province
     * @param city
     * @param county
     * @param detail
     * @param receiverTel
     * @param postalCode
     * @param status
     * @return
     */
    @PostMapping("/add")
    public Result addAddress(String userId,String receiverName,
                             String province,String city,String county,String detail,
                             String receiverTel,String postalCode,Byte status){
        CustomerAddress customerAddress=new CustomerAddress(userId,receiverName,
                province,city,county,detail,receiverTel,postalCode,status);
        int c=customerAddressService.addAddress(customerAddress);
        if(c==0){
            return ResultVOUtil.error("增加地址失败");
        }
        return ResultVOUtil.success();
    }

    /**
     * 修改收货地址
     * @param id
     * @param userId
     * @param receiverName
     * @param province
     * @param city
     * @param county
     * @param detail
     * @param receiverTel
     * @param postalCode
     * @param status
     * @return
     */
    @PostMapping("/update")
    public Result updateAddress(String id,String userId,String receiverName,
                                String province,String city,String county,String detail,
                                String receiverTel,String postalCode,Byte status){
        CustomerAddress customerAddress=new CustomerAddress(id,userId,receiverName,
                province,city,county,detail,receiverTel,postalCode,status);
        int c=customerAddressService.updateAddress(customerAddress);
        if(c==0){
            return ResultVOUtil.error("修改地址失败");
        }
        return ResultVOUtil.success();
    }

    /**
     * 删除收货地址
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Result deleteAddress(String id){
        int c=customerAddressService.deleteAddress(id);
        if(c==0){
            return ResultVOUtil.error("删除地址失败");
        }
        return ResultVOUtil.success();
    }
}
