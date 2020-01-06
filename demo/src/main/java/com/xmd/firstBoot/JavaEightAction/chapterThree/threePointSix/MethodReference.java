package com.xmd.firstBoot.JavaEightAction.chapterThree.threePointSix;

import com.xmd.firstBoot.JavaEightAction.interfance.TriFunction;
import com.xmd.firstBoot.entity.Apple;
import com.xmd.firstBoot.entity.Color;
import com.xmd.firstBoot.JavaEightAction.interfance.Fruit;
import com.xmd.firstBoot.entity.Orange;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;


import static java.util.Comparator.comparing;

/**
 * @Author: xx
 * @Description: 方法引用
 * @Date: Created in 10:03 2020/1/6
 */
public class MethodReference {

    public static void main(String[] args) {

        //先前：
        //inventory.sort((Apple a1, Apple a2)-> a1.getWeight().compareTo(a2.getWeight()));

        //之后（使用方法引用和java.util.Comparator.comparing）：
        //第一个方法引用！
        //inventory.sort(comparing(Apple::getWeight));


        //        表3-4 Lambda及其等效方法引用的例子
        //            Lambda                          等效的方法引用
        //(Apple a) -> a.getWeight()               Apple::getWeight
        //() -> Thread.currentThread().dumpStack() Thread.currentThread()::dumpStack
        //(str, i) -> str.substring(i)             String::substring
        //(String s) -> System.out.println(s)      System.out::println

        //如何构建方法引用

        //方法引用主要有三类。
        //(1) 指向静态方法的方法引用（例如Integer的parseInt方法，写作Integer::parseInt）。
        //(2) 指 向 任意类型实例方法 的方法引用（例如 String 的 length 方法，写作String::length）。
        //(3) 指向现有对象的实例方法的方法引用（假设你有一个局部变量expensiveTransaction
        //用于存放Transaction类型的对象，它支持实例方法getValue，那么你就可以写expensiveTransaction::getValue）。
        //第三种
        //Lambda表达式 ()->expensiveTransaction.getValue()可以写作expensiveTransaction::getValue


        //栗子
        //你想要对一个字符串的List排序，忽略大
        //小写。List的sort方法需要一个Comparator作为参数。你在前面看到了，Comparator描述了
        //一个具有(T, T) -> int签名的函数描述符。你可以利用String类中的compareToIgnoreCase
        //方法来定义一个Lambda表达式（注意compareToIgnoreCase是String类中预先定义的）

        //List<String> str = Arrays.asList("a","b","A","B");
        //str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));

        List<String> str = Arrays.asList("a","b","C","d");
        str.sort(String::compareToIgnoreCase);

        //3.6.2 构造函数引用

        //对于一个现有构造函数，你可以利用它的名称和关键字new来创建它的一个引用：
        //ClassName::new。它的功能与指向静态方法的引用类似。例如，假设有一个构造函数没有参数。
        //它适合Supplier的签名() -> Apple。你可以这样做：


        //构造函数引用指向默认的Apple()构造函数
        //Supplier<Apple> c1 = Apple::new;
        //调用Supplier的get方法将产生一个新的Apple
        //Apple a1 = c1.get();

        //这就等价于：

        //利用默认构造函数创建Apple的Lambda表达式
        Supplier<Apple> c1 = () -> new Apple();
        //调用Supplier的get方法将产生一个新的Apple
        Apple a1 = c1.get();

        //如果你的构造函数的签名是Apple(Integer weight)，那么它就适合Function接口的签
        //名，于是你可以这样写：

        //指向Apple(double weight)的构造函数引用
        //Function<Double,Apple> c2 = Apple::new;
        //调用该Function函数的apply方法，并给出要求的重量，将产生一个Apple
        //Apple a2 = c2.apply(100D);

        //这就等价于：
        Function<Double,Apple> c2 = (weight) -> new Apple(weight);
        //调用该Function函数的apply方法，并给出要求的重量，将产生一个Apple
        Apple a2 = c2.apply(100D);

        //在下面的代码中，一个由Integer构成的List中的每个元素都通过我们前面定义的类似的
        //map方法传递给了Apple的构造函数，得到了一个具有不同重量苹果的List

        List<Double> weights = Arrays.asList(7D,3D,4D,10D,9D);
        //将构造函数引用传递给map方法
        List<Apple> apples = map(weights,Apple::new);


        //如果你有一个具有两个参数的构造函数Apple(String color, Integer weight)，那么
        //它就适合BiFunction接口的签名，于是你可以这样写：

        //指向Apple(String color,Integer weight)的构造函数引用
        //BiFunction<Double,String,Apple> c3 = Apple::new;
        //调用该BiFunction函数的apply方法，并给出要求的颜色和重量，将产生一个新的Apple对象
        //Apple a3 = c3.apply(100D,"green");

        //这就等价于：

        //用要求的颜色和重量创建一个 Apple的Lambda表达式
        BiFunction<Double,String,Apple> c3 = (weight,color) ->new Apple(weight,color);
        //调用该BiFunction函数的apply方法，并给出要求的颜色和重量，将产生一个新的Apple对象
        Apple a3 = c3.apply(100D,"green");


        //现在你可以像下面这样使用构造函数引用了：
        TriFunction<Integer, Integer, Integer, Color> colorFactory = Color::new;
    }


    //不将构造函数实例化却能够引用它，这个功能有一些有趣的应用

    //你可以使用Map来
    //将构造函数映射到字符串值。你可以创建一个giveMeFruit方法，给它一个String和一个
    //Integer，它就可以创建出不同重量的各种水果

    static Map<String,Function<Double,Fruit>> map = new HashMap<>();


    static {
        map.put("apple",Apple::new);
        map.put("orange", Orange::new);
        //...
    }

    public static Fruit getMeFruit(String fruit,Double weight){

        return
                //你用map得到了一个Function<Double,Fruit>
                map.get(fruit.toLowerCase()).
                //用Integer类型的weight参数调用Function的apply()方法将提供所要求的Fruit
                apply(weight);
    }





    public static List<Apple> map(List<Double> list,Function<Double,Apple> f){
        List<Apple> result = new ArrayList<>();
        for(Double d : list){
            result.add(f.apply(d));
        }
        return result;
    }
}
