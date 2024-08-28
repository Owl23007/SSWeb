package com.SSweb.blog.exception;


import com.SSweb.blog.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 全局异常处理
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理所有异常
    @ExceptionHandler(Exception.class)
    public Result handException(Exception e) {
        // 打印异常信息
        e.printStackTrace();
        // 返回错误信息
        return Result.error(StringUtils.hasLength(e.getMessage())?e.getMessage():"操作失败。");
    }
}
