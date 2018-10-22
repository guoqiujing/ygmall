package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.dto.FirAndSecDTO;
import cn.myzqu.ygmall.dto.StatisticsForWeekDTO;
import cn.myzqu.ygmall.dto.UserAndPayUserDTO;
import cn.myzqu.ygmall.dto.WeeklyBuyUserDTO;
import cn.myzqu.ygmall.pojo.Account;

import java.util.List;

public interface AccountMapper {

    int deleteByPrimaryKey(String id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    UserAndPayUserDTO selectUserAndPayUser();

    int selectNewUser();

    FirAndSecDTO selectFirAndSec();

    List<WeeklyBuyUserDTO> selectWeeklyBuyUser();
}