package com.xmd.firstBoot.entity;

/**
 * @Author: xx
 * @Description: 颜色bean
 * @Date: Created in 11:22 2020/1/6
 */
public class Color {

    private Integer red;

    private Integer green;

    private Integer blue;



    public Color(Integer red, Integer green, Integer blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Integer getRed() {
        return red;
    }

    public Integer getGreen() {
        return green;
    }

    public Integer getBlue() {
        return blue;
    }
}
