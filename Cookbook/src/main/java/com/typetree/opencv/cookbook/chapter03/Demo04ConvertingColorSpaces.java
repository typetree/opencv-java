package com.typetree.opencv.cookbook.chapter03;



import org.bytedeco.opencv.opencv_core.Mat;

import static com.typetree.opencv.cookbook.OpencvUtil.showImage;
import static com.typetree.opencv.cookbook.chapter03.Demo01ColorDetector.ColorDetector;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

public class Demo04ConvertingColorSpaces {

    public static void main(String[] args) {

        Mat image = imread("Cookbook/data/boldt.jpg");

        ColorLab colorLab = new ColorLab(74.3705, -9.0003, -25.9781);

        Mat mat = ColorDetectorLab.process(image, colorLab, 30);

        showImage(mat);
    }
}
