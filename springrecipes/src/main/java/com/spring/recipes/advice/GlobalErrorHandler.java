package com.spring.recipes.advice;

import com.spring.recipes.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * 异常统一处理
 */
@RestControllerAdvice
public class GlobalErrorHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalErrorHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseModel methodArgumentNotValidException(MethodArgumentNotValidException ex){
        return ResponseModel.fail("0001",ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public ResponseModel sqlErrorHandler(Throwable ex){
        return ResponseModel.fail("0002", ex.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseModel globalErrorHandler(Throwable ex){
        return ResponseModel.fail("0003", ex.getMessage());
    }
}
