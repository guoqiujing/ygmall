package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.AccountMapper;
import cn.myzqu.ygmall.dao.CustomerMapper;
import cn.myzqu.ygmall.exception.CustomException;
import cn.myzqu.ygmall.pojo.Account;
import cn.myzqu.ygmall.pojo.Customer;
import cn.myzqu.ygmall.service.AccountService;
import cn.myzqu.ygmall.utils.KeyUtil;
import cn.myzqu.ygmall.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by 的川 on 2018/9/6.
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer addCustomer(String telephone, String email, String password) {

        if(StringUtils.isEmpty(telephone)||StringUtils.isEmpty(email)){
            throw new CustomException(1,"手机号码和邮箱不能为空");
        }
        //检查手机号码是否已经存在
        if(customerMapper.selectByTelephone(telephone)!=null){
            throw new CustomException(1,"该手机号码已经注册");
        }
        //检查邮箱是否已经存在
        if(customerMapper.selectByEmail(email)!=null){
            throw new CustomException(1,"该邮箱已经注册");
        }
        //生成唯一id
        String id = KeyUtil.getUUID();
        //加密密码
        //生成盐
        String salt = KeyUtil.getSalt();
        String encryptPassword = MD5Utils.encrypt(password,salt);
        //封装Account
        Account account = new Account(id,encryptPassword,salt);
        if(accountMapper.insertSelective(account)>0){
            log.info("新增账号成功：{}",account);
            //封装顾客信息
            Customer customer = new Customer(id,telephone,email);
            if(customerMapper.insertSelective(customer)>0){
                log.info("新增顾客信息成功：{}",customer);
                return customer;
            }
            return null;
        }
        return null;
    }

    @Override
    public Boolean login(String code, String password) {
        //根据手机号码查找信息
        Customer customer = customerMapper.selectByTelephone(code);
        if(StringUtils.isEmpty(customer)){
            customer = customerMapper.selectByEmail(code);
        }
        if(StringUtils.isEmpty(customer)){
            throw new CustomException(1,"账号或者密码错误！");
        }
        //核验密码是否正确
        Account object = accountMapper.selectByPrimaryKey(customer.getId());
        String encryptPassword = MD5Utils.encrypt(password,object.getSalt());
        if(!encryptPassword.equals(object.getPassword())){
            throw new CustomException(1,"账号或者密码错误！");
        }
        return true;
    }
}
