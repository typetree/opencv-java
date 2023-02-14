package com.typetree.opencv.cookbook.chapter04;

import org.bytedeco.opencv.opencv_core.Mat;

import static com.typetree.opencv.cookbook.OpencvUtil.showImage;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

public class Demo05EqualizeHistogram {

    public static void main(String[] args) {
        Mat image = imread("Cookbook/data/group.jpg", IMREAD_GRAYSCALE);
        showImage(image);

        Mat dest =  Histogram1D.equalize(image);
        showImage(dest);

        showImage(new Histogram1D().getHistogramImage(dest));
    }
}
