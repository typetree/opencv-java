package com.typetree.opencv.cookbook.chapter02;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;

import static com.typetree.opencv.cookbook.OpencvUtil.showImage;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

public class Demo05ROILogo {

    public static void main(String[] args) {
        Mat image = imread("Cookbook/data/puppy.bmp");
        Mat logo = imread("Cookbook/data/logo.bmp");
        Mat mask = imread("Cookbook/data/logo.bmp", IMREAD_GRAYSCALE);

        Mat imageRoi = new Mat(image, new Rect(image.cols() - logo.cols(), image.rows() - logo.rows(), logo.cols(), logo.rows()));

        logo.copyTo(imageRoi, mask);

        showImage(image);
    }


}
