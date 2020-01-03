package com.xmd.firstBoot.JavaEightAction.chapterThree.threePointOne;

import com.xmd.firstBoot.entity.Apple;

import java.util.Comparator;

/**
 * @Author: xx
 * @Description: Lambda管中窥豹
 * @Date: Created in 10:07 2020/1/3
 */
public class LambdaLeopardInTheTube {

    public static void main(String[] args) {

        //先前
        Comparator<Apple> byWeight = new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2){
                return a1.getWeight().compareTo(a2.getWeight());
            }
        };

        //之后（用了Lambda表达式）：
        Comparator<Apple> byLambdaWeight = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());


        //Lambda表达式有三个部分

        //1.参数列表——这里它采用了Comparator中compare方法的参数，两个Apple。
        //2.箭头——箭头->把参数列表与Lambda主体分隔开。
        //3.Lambda主体——比较两个Apple的重量。表达式就是Lambda的返回值了。

        //栗子如下

        //第一个Lambda表达式具有一个String类型的参数并返回一个int。Lambda没有return语句，因为已经隐含了return
        //(String s) -> s.length();

        //第二个Lambda表达式有一个Apple 类型的参数并返回一个boolean（苹果的重量是否超过150克）
        //(Apple a) -> a.getWeight() > 150


        //第三个Lambda表达式具有两个int类型的参数而没有返回值（void返回）。注意Lambda表达式可以包含多行语句，这里是两行
        //(int x, int y) -> { System.out.println("Result:"); System.out.println(x+y);

        //第四个Lambda表达式没有参数，返回一个int
        //() -> 42

        //第五个Lambda表达式具有两个Apple类型的参数，返回一个int：比较两个Apple的重量
        //(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())

        //Lambda示例


        //布尔表达式 (List<String> list) -> list.isEmpty()
        //创建对象 () -> new Apple(10)
        //消费一个对象 (Apple a) -> { System.out.println(a.getWeight()); }
        //从一个对象中选择/抽取 (String s) -> s.length()
        //组合两个值 (int a, int b) -> a * b
        //比较两个对象 (Apple a1, Apple a2) ->a1.getWeight().compareTo(a2.getWeight())

    }
}
