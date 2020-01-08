package com.xmd.firstBoot.JavaEightAction.chapterFour.fourPointOne;

import com.xmd.firstBoot.entity.Dish;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * @Author: xx
 * @Description:
 * @Date: Created in 15:13 2020/1/8
 */
public class WhatIsTheStream {

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

    public static void main(String[] args) {


        //之前（Java 7）：

        List<Dish> lowCaloricDishes = new ArrayList<>();

        //用累加器筛选元素
        for(Dish d: menu){
            if(d.getCalories() < 400){
                lowCaloricDishes.add(d);
            }
        }

        //用匿名类对菜肴排序
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });

        //处理排序后的菜名列表
        List<String> lowCaloricDishesNameOld = new ArrayList<>();
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesNameOld.add(d.getName());
        }


        //在这段代码中，你用了一个“垃圾变量”lowCaloricDishes。它唯一的作用就是作为一次
        //性的中间容器。在Java 8中，实现的细节被放在它本该归属的库里了。

        List<String> lowCaloricDishesNameNew = menu.stream()
                //选出400卡路里以下的菜肴
                .filter(x->x.getCalories()<400)
                //按照卡路提取菜肴
                .sorted(comparing(Dish::getCalories))
                //提取菜肴 里排序的名称
                .map(Dish::getName)
                .collect(toList());


        //按照Map里面的类别对菜肴进行分组
        Map<Dish.Type, List<Dish>> dishesByType =menu.stream().
                collect(groupingBy(Dish::getType));




    }

}
