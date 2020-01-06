package com.xmd.firstBoot.JavaEightAction.chapterThree.threePointFour;

import com.xmd.firstBoot.entity.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

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

        //装箱拆箱

        //但这在性能方面是要付出代价的。装箱后的值本质上就是把原始类型包裹起来，并保存在堆
        //里。因此，装箱后的值需要更多的内存，并需要额外的内存搜索来获取被包裹的原始值。
        List<Integer> list = new ArrayList<>();
        for (int i = 300; i < 400; i++){
            list.add(i);
        }

        //Java 8为我们前面所说的函数式接口带来了一个专门的版本，以便在输入和输出都是原始类
        //型时避免自动装箱的操作。

        //无装箱
        IntPredicate evenNumbers = (int i) -> i%2 == 0;
        evenNumbers.test(100);
        //返回true

        //装箱
        Predicate<Integer> oddNumbers = (Integer i )-> i%2 == 1;
        oddNumbers.test(1000);
        //返回false


        //一般来说，针对专门的输入参数类型的函数式接口的名称都要加上对应的原始类型前缀，比
        //如DoublePredicate、IntConsumer、LongBinaryOperator、IntFunction等

        //Function接口还有针对输出参数类型的变种：ToIntFunction<T>、IntToDoubleFunction等

        //                  表3-2 Java 8中的常用函数式接口
        //函数式接口               函数描述符                   原始类型特化
        //Predicate<T>            T->boolean            IntPredicate,LongPredicate, DoublePredicate
        //Consumer<T>             T->void               IntConsumer,LongConsumer, DoubleConsumer
        //Function<T,R>           T->R                  IntFunction<R>,
        //                                              IntToDoubleFunction,
        //                                              IntToLongFunction,
        //                                              LongFunction<R>,
        //                                              LongToDoubleFunction,
        //                                              LongToIntFunction,
        //                                              DoubleFunction<R>,
        //                                              ToIntFunction<T>,
        //                                              ToDoubleFunction<T>,
        //                                              ToLongFunction<T>
        //Supplier<T>           ()->T                   BooleanSupplier,IntSupplier, LongSupplier,DoubleSupplier
        //UnaryOperator<T>       T->T                   IntUnaryOperator,LongUnaryOperator,DoubleUnaryOperator
        //BinaryOperator<T>     (T,T)->T                IntBinaryOperator,LongBinaryOperator,DoubleBinaryOperator
        //BiPredicate<L,R>      (L,R)->boolean
        //BiConsumer<T,U>       (T,U)->void             ObjIntConsumer<T>,ObjLongConsumer<T>,ObjDoubleConsumer<T>
        //BiFunction<T,U,R>     (T,U)->R                ToIntBiFunction<T,U>,ToLongBiFunction<T,U>,ToDoubleBiFunction<T,U>







        //                      表3-3 Lambdas及函数式接口的例子
        //使用案例                     Lambda 的例子                                  对应的函数式接口
        //布尔表达式        (List<String> list) -> list.isEmpty()                 Predicate<List<String>>
        //创建对象          () -> new Apple(10)                                   Supplier<Apple>
        //消费一个对象      (Apple a) ->System.out.println(a.getWeight())         Consumer<Apple>
        //从一个对象中选择/提取 (String s) -> s.length()                           Function<String, Integer>或 oIntFunction<String>
        //合并两个值       (int a, int b) -> a * b                                IntBinaryOperator
        //比较两个对象     (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()) Comparator<Apple> 或 iFunction<Apple, Apple, Integer> 或 ToIntBiFunction<Apple, Apple>


       //异常、Lambda，还有函数式接口又是怎么回事呢？

       //请注意，任何函数式接口都不允许抛出受检异常（checked exception）。如果你需要Lambda
       //表达式来抛出异常，有两种办法：定义一个自己的函数式接口，并声明受检异常，或者把Lambda
       //包在一个try/catch块中




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
