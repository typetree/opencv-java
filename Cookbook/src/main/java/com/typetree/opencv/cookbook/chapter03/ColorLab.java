package com.typetree.opencv.cookbook.chapter03;

import lombok.Data;
import scala.Int;

@Data
public class ColorLab {

    private Integer lAsUInt8;

    private Integer aAsUInt8;

    private Integer bAsUInt8;

    public ColorLab(Double l, Double a, Double b){
        this.lAsUInt8 = (int)(l*255/100);
        this.aAsUInt8 = (int)(a+128);
        this.bAsUInt8 = (int)(b+128);
    }
}
