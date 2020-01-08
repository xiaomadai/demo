package com.xmd.firstBoot.JavaEightAction.dateSource;

import com.xmd.firstBoot.entity.Dish;
import com.xmd.firstBoot.entity.Trader;
import com.xmd.firstBoot.entity.Transaction;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: xx
 * @Description: 数据
 * @Date: Created in 16:15 2020/1/8
 */
public final class Date {

    public static final List<Dish> menu = Arrays.asList(
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

    private static final Trader raoul = new Trader("Raoul", "Cambridge");
    private static final Trader mario = new Trader("Mario","Milan");
    private static final Trader alan = new Trader("Alan","Cambridge");
    private static final Trader brian = new Trader("Brian","Cambridge");



    public static final List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );


}
