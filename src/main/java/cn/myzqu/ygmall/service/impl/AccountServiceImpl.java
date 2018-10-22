package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.AccountMapper;
import cn.myzqu.ygmall.dao.CustomerMapper;
import cn.myzqu.ygmall.dto.FirAndSecDTO;
import cn.myzqu.ygmall.dto.UserAndPayUserDTO;
import cn.myzqu.ygmall.dto.UserSessionDTO;
import cn.myzqu.ygmall.dto.WeeklyBuyUserDTO;
import cn.myzqu.ygmall.enums.ResultEnum;
import cn.myzqu.ygmall.exception.CustomException;
import cn.myzqu.ygmall.pojo.Account;
import cn.myzqu.ygmall.pojo.Customer;
import cn.myzqu.ygmall.service.AccountService;
import cn.myzqu.ygmall.utils.KeyUtil;
import cn.myzqu.ygmall.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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
    public int findNewUser() {
        return accountMapper.selectNewUser();
    }

    @Override
    public FirAndSecDTO findFirAndSec() {
        return accountMapper.selectFirAndSec();
    }

    @Override
    public UserAndPayUserDTO findUserAndPayUser() {
        return accountMapper.selectUserAndPayUser();
    }

    @Override
    public List<WeeklyBuyUserDTO> findWeeklyBuyUser() {
        return accountMapper.selectWeeklyBuyUser();
    }

    @Override
    public Customer addCustomer(String telephone, String email, String password) {
        if(StringUtils.isEmpty(telephone)||StringUtils.isEmpty(email)){
            throw new CustomException(ResultEnum.LOGIN_FAIL1);
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
            Byte sex=-1;
            String icon="https://ygmall-user-1255574204.cos.ap-guangzhou.myqcloud.com/a1bb4de56d414dca8d9ec7212f29961e.jpg";
            Customer customer = new Customer(id,telephone,email,sex,icon);
            if(customerMapper.insertSelective(customer)>0){
                log.info("新增顾客信息成功：{}",customer);
                return customer;
            }
            return null;
        }
        return null;
    }

    @Override
    public Customer addCustomer(String telephone, String email,
                                String password,Byte sex,String niceName) {

        if(StringUtils.isEmpty(telephone)||StringUtils.isEmpty(email)){
            throw new CustomException(ResultEnum.LOGIN_FAIL1);
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
        String id = KeyUtil.genUniqueKey();
        //加密密码
        //生成盐
        String salt = KeyUtil.getSalt();
        String encryptPassword = MD5Utils.encrypt(password,salt);
        //封装Account
        Account account = new Account(id,encryptPassword,salt);
        if(accountMapper.insertSelective(account)>0){
            log.info("新增账号成功：{}",account);
            //封装顾客信息
            String icon="https://ygmall-user-1255574204.cos.ap-guangzhou.myqcloud.com/a1bb4de56d414dca8d9ec7212f29961e.jpg";
            Customer customer = new Customer(id,telephone,email,sex,icon);
            customer.setNickName(niceName);
            if(customerMapper.insertSelective(customer)>0){
                log.info("新增顾客信息成功：{}",customer);
                return customer;
            }
            return null;
        }
        return null;
    }

    @Override
    public UserSessionDTO login(String code, String password) {
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
        UserSessionDTO userSessionDTO=new UserSessionDTO();
        BeanUtils.copyProperties(customer,userSessionDTO);
        BeanUtils.copyProperties(object,userSessionDTO);
        System.out.println("全部用户信息"+userSessionDTO);
        return userSessionDTO;
    }

    //修改密码
    @Override
    public int updatePassword(String id, String oldPwd, String newPwd){
        Account account = accountMapper.selectByPrimaryKey(id);
        //将接收到的密码转为加密密码
        String encryptPassword = MD5Utils.encrypt(oldPwd,account.getSalt());
        //将刚加密的密码与数据库中的加密密码对比
        if(!encryptPassword.equals(account.getPassword())){
            throw new CustomException(1,"密码错误！");
        }
        else{//密码正确，开始修改密码
            String encryptPwd = MD5Utils.encrypt(newPwd,account.getSalt());
            Account newAccount=new Account(id,encryptPwd);
            return accountMapper.updateByPrimaryKeySelective(newAccount);
        }

    }
}
