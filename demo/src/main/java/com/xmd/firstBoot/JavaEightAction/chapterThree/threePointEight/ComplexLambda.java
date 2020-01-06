package com.xmd.firstBoot.JavaEightAction.chapterThree.threePointEight;

import com.xmd.firstBoot.entity.Apple;
import io.swagger.models.auth.In;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @Author: xx
 * @Description: 复合 Lambda 表达式的有用方法
 * @Date: Created in 13:52 2020/1/6
 */
public class ComplexLambda {

    private static List<Apple> inventory = Arrays.asList(new Apple(3D),new Apple(6D),new Apple(2D));

    //private static List<Apple> redApple = Arrays.asList(new Apple(3D,"red"),new Apple(6D,"green"),new Apple(2D,"red"));

    public static void main(String[] args) {


        //Java 8的好几个函数式接口都有为方便而设计的方法。具体而言，许多函数式接口，比如用
        //于传递Lambda表达式的Comparator、Function和Predicate都提供了允许你进行复合的方法。
        //这是什么意思呢？在实践中，这意味着你可以把多个简单的Lambda复合成复杂的表达式。比如，
        //你可以让两个谓词之间做一个or操作，组合成一个更大的谓词。而且，你还可以让一个函数的结
        //果成为另一个函数的输入。你可能会想，函数式接口中怎么可能有更多的方法呢？（毕竟，这违
        //背了函数式接口的定义啊！）窍门在于，我们即将介绍的方法都是默认方法，也就是说它们不是
        //抽象方法。

        //3.8.1 比较器复合

        //你可以使用静态方法Comparator.comparing，根据提取用于比较的键值
        //的Function来返回一个Comparator
        Comparator<Apple> c = Comparator.comparing(Apple::getWeight);

        //1. 逆序
        //你想要对苹果按重量递减排序怎么办

        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());

        //2. 比较器链
        //如果发现有两个苹果一样重怎么办？

        inventory.sort(
                Comparator.comparing(Apple::getWeight)
                          //按重量递减排序
                          .reversed()
                          //两个苹果一样重时，进一步按国家排序
                          .thenComparing(Apple::getCountry)
        );


        //3.8.2 谓词复合


        //谓词接口包括三个方法：negate、and和or，让你可以重用已有的Predicate来创建更复
        //杂的谓词。比如，你可以使用negate方法来返回一个Predicate的非，比如苹果不是红的：

        Predicate<Apple> redApple = (apple) -> apple.getColor().equals("red");
        //产生现有Predicate对象redApple的非
        Predicate<Apple> notRedApple = redApple.negate();

        //你可能想要把两个Lambda用and方法组合起来，比如一个苹果既是红色又比较重：
        //链接两个谓词来生成另一个Predicate对象
        Predicate<Apple> redAndHeavyApple = redApple.and(a->a.getWeight()>150);

        //你可以进一步组合谓词，表达要么是重（150克以上）的红苹果，要么是绿苹果：
        Predicate<Apple> redAndHeavyAppleOrGreen = redApple
                //链接Predicate的方法来构造更复杂Predicate对象
                .and(a->a.getWeight()>150)
                .or(a->a.getColor().equals("green"));



        //3.8.3 函数复合

        //最后，你还可以把Function接口所代表的Lambda表达式复合起来。Function接口为此配
        //了andThen和compose两个默认方法，它们都会返回Function的一个实例。

        //andThen方法会返回一个函数，它先对输入应用一个给定函数，再对输出应用另一个函数。
        //比如，假设有一个函数f给数字加1 (x -> x + 1)，另一个函数g给数字乘2，你可以将它们组
        //合成一个函数h，先给数字加1，再给结果乘2：

        Function<Integer,Integer> f = x -> x+1;
        Function<Integer,Integer> g = x -> x*2;
        Function<Integer,Integer> h = f.andThen(g);
        //这将返回4
        int result = h.apply(1);


        //你也可以类似地使用compose方法，先把给定的函数用作compose的参数里面给的那个函
        //数，然后再把函数本身用于结果。比如在上一个例子里用compose的话，它将意味着f(g(x))，
        //而andThen则意味着g(f(x))：


        Function<Integer,Integer> h1 = g.compose(f);
        //这将返回3
        int res = h1.apply(1);


        //那么在实际中这有什么用呢

        //比方说你有一系列工具方法，对用String表示的一封信做文本转换


        //现在你可以通过复合这些工具方法来创建各种转型流水线了，比如创建一个流水线：先加上
        //抬头，然后进行拼写检查，最后加上一个落款

        Function<String,String> addHeader = Letter::addHeader;
        Function<String,String>  transformationPipeline = addHeader.andThen(Letter::checkSpelling).andThen(Letter::addFooter);

         //第二个流水线可能只加抬头、落款，而不做拼写检查

        Function<String, String> transformationPipeline2 = addHeader.andThen(Letter::addFooter);



    }


    //信
    public static class Letter{
        public static String addHeader(String text){
            return "From Raoul, Mario and Alan: " + text;
        }
        public static String addFooter(String text){
            return text + " Kind regards";
        }
        public static String checkSpelling(String text){
            return text.replaceAll("labda", "lambda");
        }
    }

}
