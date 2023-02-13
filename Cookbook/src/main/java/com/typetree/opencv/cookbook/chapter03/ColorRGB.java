package com.typetree.opencv.cookbook.chapter03;

import lombok.Data;

import java.awt.*;

@Data
public class ColorRGB {

    private int red;
    private int green;
    private int blue;
    private Color color;

    public ColorRGB(int red, int green, int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.color = new Color(red, green, blue);
    }

    public static ColorRGB fromBGR(int[] arr){
        if(arr==null||arr.length!=3){
            return null;
        }
        return new ColorRGB(arr[2], arr[1], arr[0]);
    }
}
