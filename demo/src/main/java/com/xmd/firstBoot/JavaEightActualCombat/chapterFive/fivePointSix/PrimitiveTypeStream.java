package com.xmd.firstBoot.JavaEightActualCombat.chapterFive.fivePointSix;

import com.google.common.primitives.Ints;
import com.xmd.firstBoot.entity.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: xx
 * @Description: 原始类型流
 * @Date: Created in 16:54 2019/12/31
 */
public class PrimitiveTypeStream {

    private static  List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );

    public static void main(String args[]) {


        //5.6.1 原始类型流特化

        //1. 映射到数值流
        int calories = menu.stream()
                //返回一个Stream<Dish>
                .mapToInt(Dish::getCalories)
                //返回一个IntStream
                .sum();

        //请注意，如果流是空的，sum默认返回0。IntStream还支持其他的方便方法，如 max、min、average等


        //2. 转换回对象流
        //将Stream转换为数值流
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        //将数值流转换为Stream
        Stream<Integer> stream = intStream.boxed();

        //要把原始流转换成一般流（每个int都会装箱成一个Integer），可以使用boxed方法

        //3.要找到IntStream中的最大元素，可以调用max方法，它会返回一个OptionalInt：

        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();

        //现在，如果没有最大值的话，你就可以显式处理OptionalInt去定义一个默认值了：
        //如果没有最大值的话，显式提供一个默认最大值
        int max = maxCalories.orElse(1);

        //5.6.2 数值范围

        //Java 8引入了两个可以用于IntStream和LongStream的静态方法，帮助生成这种范围：
        //range和rangeClosed。这两个方法都是第一个参数接受起始值，第二个参数接受结束值

        //栗子

        IntStream evenNumbers = IntStream
                 //表示范围[1, 100]
                .rangeClosed(1, 100)
                //一个从1到100的偶数流
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());

        //这里我们用了rangeClosed方法来生成1到100之间的所有数字。它会产生一个流，然后你
        //可以链接filter方法，只选出偶数。到目前为止还没有进行任何计算。最后，你对生成的流调
        //用count。因为count是一个终端操作，所以它会处理流，并返回结果50，这正是1到100（包括
        //两端）中所有偶数的个数。请注意，比较一下，如果改用IntStream.range(1, 100)，则结果
        //将会是49个偶数，因为range是不包含结束值的。

        //5.6.3 数值流应用：勾股数

        //1. 勾股数
        //a * a + b * b = c * c\

        //2. 表示三元数
        //new int[]{3, 4, 5} 来表示勾股 数(3, 4, 5) 可以用数组索引访问每个元素了

        //3. 筛选成立的组合
        //需要测试a * a + b * b的平方根是不是整数 在Java里可以使用expr % 1表示。如果它不是整数，那就是说c不是整数
        /*filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)*/

        //4. 生成三元组a和b能够组成一个正确的组合。
        //现在需要创建一个三元组。你可以使用map操作，像下面这样把每个元素转换成一个勾股数组：
        /*stream.filter(b -> Math.sqrt(a*a + b*b) % 1 == 0).map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});*/

        //5. 生成b值
        //Stream.rangeClosed让你可以在给定区间内生成一个数值流。你可以用它来给b提供数值，这里是1到100
        /*IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                .boxed()
                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});*/
        //请注意，你在filter之后调用boxed， 从rangeClosed返回的IntStream生成一个
        //Stream<Integer>。这是因为你的map会为流中的每个元素返回一个int数组。而IntStream
        //中的map方法只能为流中的每个元素返回另一个int，这可不是你想要的！你可以用IntStream
        //的mapToObj方法改写它，这个方法会返回一个对象值流：
        /*IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});*/

        //6. 生成值
        //这里有一个关键的假设：给出了a的值。 现在，只要已知a的值，你就有了一个可以生成勾
        //股数的流。如何解决这个问题呢？就像b一样，你需要为a生成数值！最终的解决方案如下所示：

        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );

        //好的，flatMap又是怎么回事呢？首先，创建一个从1到100的数值范围来生成a的值。对每
        //个给定的a值，创建一个三元数流。要是把a的值映射到三元数流的话，就会得到一个由流构成的
        //流。flatMap方法在做映射的同时，还会把所有生成的三元数流扁平化成一个流。这样你就得到
        //了一个三元数流。还要注意，我们把b的范围改成了a到100。没有必要再从1开始了，否则就会造
        //成重复的三元数，例如(3,4,5)和(4,3,5)。

        //7. 运行代码

        pythagoreanTriples.limit(5).forEach(t-> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        //8. 你还能做得更好吗？
        //目前的解决办法并不是最优的，因为你要求两次平方根。让代码更为紧凑的一种可能的方法
        //是，先生成所有的三元数(a*a, b*b, a*a+b*b)，然后再筛选符合条件的：

        Stream<int[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0)
                );

        pythagoreanTriples2.limit(5).forEach(t-> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

    }





}
