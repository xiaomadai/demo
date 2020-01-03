package com.xmd.firstBoot.entity;

import com.xmd.firstBoot.enums.CaloricLevel;

/**
 * @Author: xx
 * @Description:
 * @Date: Created in 16:48 2019/12/31
 */
public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public CaloricLevel getCaloricLevel(){
        if (this.getCalories() <= 400) {
            return CaloricLevel.DIET;
        }else if (this.getCalories() <= 700) {
            return CaloricLevel.NORMAL;
        }else {
            return CaloricLevel.FAT;
        }
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }

    public enum Type { MEAT, FISH, OTHER }
}
