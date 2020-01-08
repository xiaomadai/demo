package com.xmd.firstBoot.JavaEightAction.chapterFour.fourPointFour;

import com.xmd.firstBoot.JavaEightAction.dateSource.Date;
import com.xmd.firstBoot.entity.Dish;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @Author: xx
 * @Description: 流操作
 * @Date: Created in 16:14 2020/1/8
 */
public class StreamOperation {

    private static final List<Dish> menu = Date.menu;

    public static void main(String[] args) {

        List<String> names = menu.stream()
                //中间操作
                .filter(x->x.getCalories()>400)
                //中间操作
                .map(Dish::getName)
                //中间操作
                .limit(3)
                //将Stream转换为List
                .collect(toList());

        //4.4.1 中间操作
        List<String> namesTest = menu.stream()
                        .filter(d -> {
                            System.out.println("filtering" + " " + d.getName());
                            return d.getCalories() > 300;
                        })
                        .map(d -> {
                            System.out.println("mapping" + " " + d.getName());
                            return d.getName();
                        })
                        .limit(3)
                        .collect(toList());


        System.out.println("------" + namesTest);


        //有好几种优化利用了流的延迟性质。
        //第一，尽管很多菜的热量都高于300卡路里，但只选出了前三个！这是因为limit操作和一种称为短路的技巧，我们会在下一章中解释。
        //第二，尽管filter和map是两个独立的操作，但它们合并到同一次遍历中了（我们把这种技术叫作循环合并）。

        //4.4.2 终端操作


        //终端操作会从流的流水线生成结果。其结果是任何不是流的值，比如List、Integer，甚
        //至void。

        menu.stream().forEach(System.out::println);

        //4.4.3 使用流

        //总而言之，流的使用一般包括三件事：
        //一个数据源（如集合）来执行一个查询；
        //一个中间操作链，形成一条流的流水线；
        //一个终端操作，执行流水线，并能生成结果。



    }


}
