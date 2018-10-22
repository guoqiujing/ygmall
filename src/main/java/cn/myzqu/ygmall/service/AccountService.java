package cn.myzqu.ygmall.service;

import cn.myzqu.ygmall.dto.FirAndSecDTO;
import cn.myzqu.ygmall.dto.UserAndPayUserDTO;
import cn.myzqu.ygmall.dto.UserSessionDTO;
import cn.myzqu.ygmall.dto.WeeklyBuyUserDTO;
import cn.myzqu.ygmall.pojo.Customer;

import java.util.List;

/**
 * Created by 的川 on 2018/9/6.
 */

public interface AccountService {

    Customer addCustomer(String telephone, String email, String password);

    Customer addCustomer(String telephone, String email,
                                String password,Byte sex,String niceName);

    UserSessionDTO login(String code, String password);

    int updatePassword(String id, String oldPwd, String newPwd);

    UserAndPayUserDTO findUserAndPayUser();

    int findNewUser();

    FirAndSecDTO findFirAndSec();

    List<WeeklyBuyUserDTO> findWeeklyBuyUser();

}
