package com.xmd.firstBoot.JavaEightAction.chapterThree.threePointSeven;

import com.xmd.firstBoot.entity.Apple;
import com.xmd.firstBoot.response.CommonResult;
import org.apache.ibatis.annotations.Insert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

/**
 * @Author: xx
 * @Description: Lambda 和方法引用实战
 * @Date: Created in 11:29 2020/1/6
 */
public class ActionOfLambdaAndMethodRef {


    private static List<Apple> inventory = Arrays.asList(new Apple(3D),new Apple(6D),new Apple(2D));

    public static void main(String[] args) {

        //用不同的排序策略给一个Apple列表排序，并需要展示如何把一个原始粗暴的解决方
        //案转变得更为简明。这会用到书中迄今讲到的所有概念和功能：行为参数化、匿名类、Lambda
        //表达式和方法引用

        //的最终解决方案是这样的
        //inventory.sort(comparing(Apple::getWeight));


        //第一个解决方案
        inventory.sort(new AppleComparator());



        //3.7.2 第 2 步：使用匿名类

        //你可以使用匿名类来改进解决方案，而不是实现一个Comparator却只实例化一次
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });



        //3.7.3 第 3 步：使用 Lambda 表达式

        //Java 8引入了Lambda表达式，它提供了一种轻量级语法来实
        //现相同的目标：传递代码。你看到了，在需要函数式接口的地方可以使用Lambda表达式。我们
        //回顾一下：函数式接口就是仅仅定义一个抽象方法的接口。抽象方法的签名（称为函数描述符）
        //描述了Lambda表达式的签名。在这个例子里，Comparator代表了函数描述符(T, T) -> int。
        //因为你用的是苹果，所以它具体代表的就是(Apple, Apple) -> int。改进后的新解决方案看
        //上去就是这样的了：


        inventory.sort((Apple a1,Apple a2) -> a1.getWeight().compareTo(a2.getWeight()) );


        //我们前面解释过了，Java编译器可以根据Lambda出现的上下文来推断Lambda表达式参数的
        //类型。那么你的解决方案就可以重写成这样：


        inventory.sort((a1,a2) -> a1.getWeight().compareTo(a2.getWeight()) );


        //代码还能变得更易读一点吗？Comparator具有一个叫作comparing的静态辅助方法，
        //它可以接受一个Function来提取Comparable键值，并生成一个Comparator对象（我们会在第
        //9章解释为什么接口可以有静态方法）。它可以像下面这样用（注意你现在传递的Lambda只有一
        //个参数：Lambda说明了如何从苹果中提取需要比较的键值）：

        Comparator<Apple> c = comparing((Apple a) -> a.getWeight());

        //现在你可以把代码再改得紧凑一点了：

        inventory.sort(comparing(a -> a.getWeight()));


        //3.7.4 第 4 步：使用方法引用


        //方法引用就是替代那些转发参数的Lambda表达式的语法糖

        inventory.sort(comparing(Apple::getWeight));

        //且代码读起来和问题描述差不多：“对库存进行排序，比较苹果的重量。
    }

    //3.7.1 第 1 步：传递代码

    //Java 8的API已经为你提供了一个List可用的sort方法，你不用自己去实现它。
    //那么最困难的部分已经搞定了！但是，如何把排序策略传递给sort方法呢？你看，sort方法的
    //签名是这样的：

    //void sort(Comparator<? super E> c)


    //它需要一个Comparator对象来比较两个Apple！这就是在Java中传递策略的方式：它们必
    //须包裹在一个对象里。我们说sort的行为被参数化了：传递给它的排序策略不同，其行为也会
    //不同。

    //你的第一个解决方案看上去是这样的：

    public static class AppleComparator implements Comparator<Apple> {

        @Override
        public int compare(Apple a1, Apple a2) {
            return a1.getWeight().compareTo(a2.getWeight());
        }
    }


}
