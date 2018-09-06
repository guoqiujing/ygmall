package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.pojo.Account;
import cn.myzqu.ygmall.pojo.Customer;
import cn.myzqu.ygmall.utils.KeyUtil;
import cn.myzqu.ygmall.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by 的川 on 2018/9/6.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext-*.xml")
public class AccountMapperTest {

    @Resource
    private AccountMapper accountMapper;

    @Test
    public void insertSelective() throws Exception {

        Account account = new Account();
        for(int i=1;i<=2;i++){
            String id = KeyUtil.getUUID();
            account.setId(id);
            //用户输入的密码
            String  sorce = "123456";
            String salt = KeyUtil.getSalt();
            account.setPassword(MD5Utils.encrypt(sorce,salt));
            account.setSalt(salt);
            account.setType(new Byte("0"));
            account.setState(new Byte("0"));
            accountMapper.insertSelective(account);
            log.info("插入账号{}成功{}",id,account);
        }


    }

}