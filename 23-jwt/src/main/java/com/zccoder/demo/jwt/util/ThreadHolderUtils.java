package com.zccoder.demo.jwt.util;

import com.zccoder.demo.jwt.domain.User;

import java.util.Objects;

import lombok.experimental.UtilityClass;

/**
 * 线程变量工具类
 *
 * @author zc
 * @date 2020/09/29
 */
@UtilityClass
public class ThreadHolderUtils {

    /**
     * 当前请求线程用户
     */
    private static final ThreadLocal<User> USER_HOLDER = new ThreadLocal<>();

    /**
     * 设置当前请求线程用户
     *
     * @param user 用户
     */
    public static void setUser(User user) {
        USER_HOLDER.set(user);
    }

    /**
     * 获取当前请求线程用户
     *
     * @return 用户
     */
    public static User getUser() {
        User user = USER_HOLDER.get();
        if (Objects.isNull(user)) {
            throw new IllegalStateException("用户信息为空！");
        }
        return user;
    }

    /**
     * 清除当前请求线程用户
     */
    public static void clearUser() {
        USER_HOLDER.remove();
    }

}
