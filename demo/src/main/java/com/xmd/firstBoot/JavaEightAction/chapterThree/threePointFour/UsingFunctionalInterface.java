package com.xmd.firstBoot.JavaEightAction.chapterThree.threePointFour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Author: xx
 * @Description: 使用函数式接口
 * @Date: Created in 14:14 2020/1/3
 */
public class UsingFunctionalInterface {

    public static void main(String[] args) {


        //函数式接口的抽象方法的签名称为函数描述符
        //Java 8的库设计师帮你在java.util.function包中引入了几个新的函数式接口
        //接下来会介绍Predicate、Consumer和Function

        //3.4.1 Predicate

        //java.util.function.Predicate<T>接口定义了一个名叫test的抽象方法，它接受泛型
        //T对象，并返回一个boolean

        //在你需要表示一个涉及类型T的布尔表达式时，就可以使用这个接口


        //比如，你可以定义一个接受String对象的Lambda表达式，如下所示。
        List<String> listOfStrings = Arrays.asList("var2","","vars","var");
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);

        //3.4.2 Consumer

        //java.util.function.Consumer<T>定义了一个名叫accept的抽象方法，它接受泛型T
        //的对象，没有返回（void）

        //你如果需要访问类型T的对象，并对其执行某些操作，就可以使用这个接口
        forEach(listOfStrings,(String s)-> System.out.println(s));

        //3.4.3 Function

        //java.util.function.Function<T, R>接口定义了一个叫作apply的方法，它接受一个
        //泛型T的对象，并返回一个泛型R的对象

        //如果你需要定义一个Lambda，将输入对象的信息映射
        //到输出，就可以使用这个接口（比如提取苹果的重量，或把字符串映射为它的长度）。

        List<Integer> l = map(
                Arrays.asList("lambdas","in","action"),
                (String s) -> s.length()
        );



    }

    //3.4.1 Predicate
    public static <T> List<T> filter(List<T> list,Predicate<T> p){
        List<T> results = new ArrayList<>();
        for(T s : list){
            if(p.test(s)){
                results.add(s);
            }
        }
        return results;
    }


    //3.4.2 Consumer
    public static <T> void forEach(List<T> list,Consumer<T> c){
        for(T i : list){
            c.accept(i);
        }
    }

    //3.4.3 Function
    public static <T,R> List<R> map(List<T> list, Function<T,R> f){
        List<R> results = new ArrayList<>();
        for(T s : list){
            results.add(f.apply(s));
        }
        return results;
    }


}
