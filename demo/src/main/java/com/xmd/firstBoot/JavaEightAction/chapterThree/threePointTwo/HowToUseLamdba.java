package com.xmd.firstBoot.JavaEightAction.chapterThree.threePointTwo;

import com.xmd.firstBoot.entity.Apple;

/**
 * @Author: xx
 * @Description: 在哪里以及如何使用 Lambda
 * @Date: Created in 10:27 2020/1/3
 */
public class HowToUseLamdba {

    public static void main(String[] args) {

        //3.2.1 函数式接口


        //就是只定义一个抽象方法的接口
        //像Comparator和Runnable

        //用函数式接口可以干什么呢

        //Lambda表达式允许你直接以内联的形式为函数式接口的抽象方法提供实现，
        //并把整个表达式作为函数式接口的实例（具体说来，是函数式接口一个具体实现
        //的实例）

        //使用Lambda
        Runnable r1 = () -> System.out.println("Hello World 1");

        //使用匿名类
        Runnable r2 = new Runnable(){
            @Override
            public void run(){
                System.out.println("Hello World 2");
            }
        };

        process(r1);
        process(r2);

        process(()-> System.out.println(("Hello World 3")));


        //3.2.2 函数描述符

        //函数式接口的抽象方法的签名基本上就是Lambda表达式的签名。我们将这种抽象方法叫作函数描述符

        //栗子
        //Runnable接口可以看作一个什么也不接受什么也不返回（void）的函数的签名，因为它只有一个叫作run的抽象方法，这个方法什么也不接受，什么也不返回（void）

        //() -> void代表了参数列表为空，且返回void的函数，这正是Runnable接口所代表的

        //(Apple,Apple) -> int  代表接受两个Apple作为参数且返回int的函数


        //process(() -> System.out.println("This is awesome!!"));
        //此代码执行时将打印“This is awesome!!”。
        //Lambda表达式()-> System.out.println("This is awesome!!")不接受参数且返回void。这恰恰是Runnable接口中run方法的签名。

        //@FunctionalInterface又是怎么回事？

        //新的Java API，会发现函数式接口带有@FunctionalInterface的标注
        //这个标注用于表示该接口会设计成一个函数式接口


    }

    public static void process(Runnable r){
        r.run();
    }

}
