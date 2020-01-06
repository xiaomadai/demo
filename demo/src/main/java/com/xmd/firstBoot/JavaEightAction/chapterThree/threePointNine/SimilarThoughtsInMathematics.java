package com.xmd.firstBoot.JavaEightAction.chapterThree.threePointNine;

import java.util.function.DoubleFunction;

/**
 * @Author: xx
 * @Description: 数学中的类似思想
 * @Date: Created in 16:22 2020/1/6
 */
public class SimilarThoughtsInMathematics {

    public static void main(String[] args) {


        //3.9.1 积分

        //假设你有一个（数学，不是Java）函数f，比如说定义是
        //              f (x) = x+10

        //那么这在Java里面如何表达呢？

        //根据第一条原则你需要一个方法，比如说叫integrate，它接受三个参数：一个是f，
        //还有上下限（这里是3.0和7.0）。于是写在Java里就是下面这个样子，函数f是被传递进去的：

        //              integrate(f, 3, 7)

        //请注意，你不能简单地写：

        //              integrate(x + 10, 3, 7)

        //原因有二。
        // 第一，x的作用域不清楚；
        // 第二，这将会把x + 10的值而不是函数f传给积分。
        //事实上，数学上dx的秘密作用就是说“以x为自变量、结果是x+10的那个函数。”


        //3.9.2 与 Java 8 的 Lambda 联系起来


        //Java 8的表示法(double x) -> x + 10（一个Lambda表达式）恰恰就是为此设计的，因此你可以写：
        //integrate((double x) -> x + 10, 3, 7);

        //或者

        //integrate((double x) -> f(x), 3, 7)

        //或者，用前面说的方法引用，只要写：
        //integrate(C::f, 3, 7)


        //这里C是包含静态方法f的一个类。理念就是把f背后的代码传给integrate方法。


    }

    //现在你可能在想如何写integrate本身了。我们还假设f是一个线性函数（直线）。你可能
    //会写成类似数学的形式：
    //错误的 Java/代码！（函数/的写法不能/像数学里那/样。）
    /*public double integrate((double -> double)f, double a, double b) {
        return (f(a)+f(b))*(b-a)/2.0
    }*/

    public double integrate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a)+f.apply(b))*(a+b)/2.0;
    }

}
