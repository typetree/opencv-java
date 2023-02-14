package com.typetree.opencv.cookbook.chapter04;

import org.bytedeco.opencv.opencv_core.Mat;

import static com.typetree.opencv.cookbook.OpencvUtil.showImage;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgproc.THRESH_BINARY;
import static org.bytedeco.opencv.global.opencv_imgproc.threshold;

public class Demo03Threshold {

    public static void main(String[] args) {
        Mat image = imread("Cookbook/data/group.jpg", IMREAD_GRAYSCALE);

        Mat dest = new Mat();

        threshold(image, dest, 60,255, THRESH_BINARY);

        showImage(dest);
    }
}
