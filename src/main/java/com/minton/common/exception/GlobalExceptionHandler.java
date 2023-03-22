package com.minton.common.exception;


import com.alibaba.fastjson2.JSON;
import com.minton.common.ret.RetInfo;
import com.minton.common.ret.RetResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import javax.security.sasl.AuthenticationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 非法参数异常
     * @param ex
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public RetInfo MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        List<String> list = new ArrayList<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            list.add(fieldError.getDefaultMessage());
        }
        Collections.sort(list);
        log.error("fieldErrors:[ex:{}]", JSON.toJSONString(list));
        return RetResult.retError("参数错误");
    }


    @ExceptionHandler(value = BadSqlGrammarException.class)
    @ResponseStatus(HttpStatus.OK)
    public RetInfo BadSqlGrammarExceptionHandler(BadSqlGrammarException ex){
        log.info("badSqlGrammarException:[exception:{}]", ex.getMessage());
        return RetResult.retError("SQL语句错误");
    }



    /**
     * HTTP解析请求参数异常
     *
     * @param e e
     * @return res
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    public RetInfo httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("httpMessageNotReadableException:[e:{}]", e.getMessage());
        return RetResult.retError("http解析请求参数异常");
    }

    /**
     * 登陆授权异常处理
     *
     * @param exception exception
     * @return res
     */
    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseStatus(HttpStatus.OK)
    public RetInfo authenticationExceptionHandler(AuthenticationException exception) {
        log.error("authenticationExceptionHandler:[exception:{}]", exception.getMessage());
        return RetResult.retError("authenticationExceptionHandler");
    }

    /**
     * 默认异常处理
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public RetInfo exceptionHandler(Exception exception) {
        log.error("exceptionHandler:[exception:{}]", exception.getMessage());
        if (Objects.nonNull(exception.getMessage())){
            return RetResult.retError(exception.getMessage());
        }
        return RetResult.retError("System Exception.");
    }

}
