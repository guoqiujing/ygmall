package cn.myzqu.ygmall.handler;

import cn.myzqu.ygmall.enums.ResultEnum;
import cn.myzqu.ygmall.exception.CustomException;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 统一异常处理器
 * Created by 的川 on 2018/5/9.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public Result customExceptionHandler(CustomException e){
        logger.error(e.getMessage(),e);
        return ResultVOUtil.error(e.getMessage());
    }


    @ExceptionHandler(BadSqlGrammarException.class)
    public Result sqlExceptionHandler(Exception e){
        logger.error(e.getMessage(),e);
        return ResultVOUtil.error(ResultEnum.SQL_ERROR);
    }

    /**
     * 处理系统异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result ExceptionHandler(Exception e){
        logger.error(e.getMessage(),e);
        return ResultVOUtil.error(ResultEnum.ERROR);
    }

}
