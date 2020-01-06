package com.xmd.firstBoot.JavaEightAction.interfance;

/**
 * @Author: xx
 * @Description: 构造函数引用的签名匹配的函数式接口
 * @Date: Created in 11:20 2020/1/6
 */
public interface TriFunction<T,U,V,R> {
    R apply(T t, U u, V v);
}
