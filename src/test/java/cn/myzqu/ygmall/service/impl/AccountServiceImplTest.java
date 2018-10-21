package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.service.AccountService;
import cn.myzqu.ygmall.utils.gen.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by 的川 on 2018/10/21.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/*.xml")
public class AccountServiceImplTest {

    @Resource
    private AccountService accountService;


    @Test
    public void addCustomer() throws Exception {
        for(int i = 1; i< 1000; i++){
            String tel = UserInfo.getTel();
            String email = UserInfo.getEmail(6,11);
            String password = "ygmall2018";
            Integer sex = UserInfo.getNum(0,1);
            String niceName = UserInfo.getChineseName(sex);
            accountService.addCustomer(tel,email,password,sex.byteValue(),niceName);
        }

    }

}