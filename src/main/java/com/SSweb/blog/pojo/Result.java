package com.SSweb.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code;   // 状态码
    private String message; // 提示信息
    private T data;         // 返回数据

    // 返回响应数据
    public static <E> Result<E> success(E data) {
        return new Result<E>(0, "success", data);
    }

    // 返回响应结果
    public static Result success() {
        return new Result(0, "success", null);
    }
    public static Result error(String message) {
        return new Result(-1, message, null);
    }
}
