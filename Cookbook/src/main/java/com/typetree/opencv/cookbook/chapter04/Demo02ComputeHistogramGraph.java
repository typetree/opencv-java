package com.typetree.opencv.cookbook.chapter04;

import org.bytedeco.opencv.opencv_core.Mat;

import java.awt.image.BufferedImage;

import static com.typetree.opencv.cookbook.OpencvUtil.showImage;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

public class Demo02ComputeHistogramGraph {

    public static void main(String[] args) {
        Mat image = imread("Cookbook/data/group.jpg",IMREAD_GRAYSCALE);
        Histogram1D histogram1D = new Histogram1D();
        BufferedImage histogramImage = histogram1D.getHistogramImage(image);
        showImage(histogramImage);
    }
}
