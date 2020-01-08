package com.xmd.firstBoot.JavaEightAction.chapterFour.fourPointThree;

import com.xmd.firstBoot.entity.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Author: xx
 * @Description: 流与集合
 * @Date: Created in 15:53 2020/1/8
 */
public class StreamsAndCollections {

    private static List<Dish> menu = Arrays.asList(
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


    public static void main(String[] args) {

        //粗略地说，集合与流之间的差异就在于什么时候进行计算。集合是一个内存中的数据结构，
        //它包含数据结构中目前所有的值——集合中的每个元素都得先算出来才能添加到集合中。（你可
        //以往集合里加东西或者删东西，但是不管什么时候，集合中的每个元素都是放在内存里的，元素
        //都得先算出来才能成为集合的一部分。）


        //相比之下，流则是在概念上固定的数据结构（你不能添加或删除元素），其元素则是按需计
        //算的。 这对编程有很大的好处。在第6章中，我们将展示构建一个质数流（2, 3, 5, 7, 11, …）有
        //多简单，尽管质数有无穷多个。这个思想就是用户仅仅从流中提取需要的值，而这些值——在用
        //户看不见的地方——只会按需生成。这是一种生产者－消费者的关系。从另一个角度来说，流就
        //像是一个延迟创建的集合：只有在消费者要求的时候才会计算值

        //4.3.1 只能遍历一次

        //请注意，和迭代器类似，流只能遍历一次。遍历完之后，我们就说这个流已经被消费掉了。

        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
        //java.lang.IllegalStateException:流已被操作或关闭
        //s.forEach(System.out::println);

        //4.3.2 外部迭代与内部迭代

        //使用Collection接口需要用户去做迭代（比如用for-each），这称为外部迭代。 相反，
        //Streams库使用内部迭代——它帮你把迭代做了，还把得到的流值存在了某个地方，你只要给出
        //一个函数说要干什么就可以了。下面的代码列表说明了这种区别。

        //显式顺序迭代菜单列表
        List<String> names = new ArrayList<>();
        //提取名称并将其添加到累加器
        for(Dish d: menu){
            names.add(d.getName());
        }

        //背后的迭代器做外部迭代
        List<String> namesIter = new ArrayList<>();
        Iterator<Dish> iterator = menu.iterator();
        while(iterator.hasNext()) {
            Dish d = iterator.next();
            namesIter.add(d.getName());
        }

        //流：内部迭代
        List<String> namesLam = menu.stream()
                .map(Dish::getName)
                .collect(toList());

        //内部迭代时，项目可以透明地并行处理，或者用更优化的顺
        //序进行处理。要是用Java过去的那种外部迭代方法，这些优化都是很困难的。这似乎有点儿鸡蛋
        //里挑骨头，但这差不多就是Java 8引入流的理由了——Streams库的内部迭代可以自动选择一种适
        //合你硬件的数据表示和并行实现。与此相反，一旦通过写for-each而选择了外部迭代，那你基
        //本上就要自己管理所有的并行问题了

    }


}
