package com.xmd.firstBoot.entity;

import com.xmd.firstBoot.JavaEightAction.interfance.Fruit;

/**
 * @Author: xx
 * @Description:
 * @Date: Created in 10:10 2020/1/3
 */
public class Apple implements Fruit {

    private  Double weight;

    private String color;

    private String country;

    public Apple() {
    }

    public Apple(Double weight) {
        this.weight = weight;
    }

    public Apple(Double weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    public String getCountry() {
        return country;
    }
}
