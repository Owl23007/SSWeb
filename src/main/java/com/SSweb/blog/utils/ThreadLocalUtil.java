package com.SSweb.blog.utils;

import java.util.Map;
/**
 * ThreadLocal 工具类
 */
@SuppressWarnings("all")

public class ThreadLocalUtil {
    private static final ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public static void set(Map<String, Object> map) {
        threadLocal.set(map);
    }

    public static Map<String, Object> get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}