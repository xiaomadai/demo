package com.xmd.firstBoot.JavaEightAction.chapterFour.fourPointTwo;

import com.xmd.firstBoot.entity.Dish;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @Author: xx
 * @Description: 流简介
 * @Date: Created in 15:38 2020/1/8
 */
public class StreamIntroduction {

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

        //流到底是什么呢？简短的定义就是“从支持数据处理操作的源生成的元素序列”

        //元素序列——就像集合一样，流也提供了一个接口，可以访问特定元素类型的一组有序
        //值。因为集合是数据结构，所以它的主要目的是以特定的时间/空间复杂度存储和访问元
        //素（如ArrayList 与 LinkedList）。但流的目的在于表达计算，比如你前面见到的
        //filter、sorted和map。集合讲的是数据，流讲的是计算。

        //源——流会使用一个提供数据的源，如集合、数组或输入/输出资源。 请注意，从有序集
        //合生成流时会保留原有的顺序。由列表生成的流，其元素顺序与列表一致。

        //数据处理操作——流的数据处理功能支持类似于数据库的操作，以及函数式编程语言中
        //的常用操作，如filter、map、reduce、find、match、sort等。流操作可以顺序执
        //行，也可并行执行

        //此外，流操作有两个重要的特点

        //流水线——很多流操作本身会返回一个流，这样多个操作就可以链接起来，形成一个大
        //的流水线。

        //内部迭代——与使用迭代器显式迭代的集合不同，流的迭代操作是在背后进行的。

        List<String>  threeHighCaloricDishNames = menu.stream()
                .filter(x->x.getCalories()>500)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());


    }


}
