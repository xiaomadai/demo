package com.xmd.firstBoot.JavaEightAction.chapterSix.sixPointThree;

import com.xmd.firstBoot.entity.Dish;
import com.xmd.firstBoot.entity.Trader;
import com.xmd.firstBoot.entity.Transaction;
import com.xmd.firstBoot.enums.CaloricLevel;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.*;

/**
 * @Author: xx
 * @Description: 分区
 * @Date: Created in 16:07 2020/1/2
 */
public class Partition {

    private static Trader raoul = new Trader("Raoul", "Cambridge");
    private static Trader mario = new Trader("Mario","Milan");
    private static Trader alan = new Trader("Alan","Cambridge");
    private static Trader brian = new Trader("Brian","Cambridge");

    private static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

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

    }

}
