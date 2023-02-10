package com.typetree.opencv.cookbook.chapter02;

import org.bytedeco.opencv.opencv_core.Mat;
import static com.typetree.opencv.cookbook.OpencvUtil.showImage;
import static org.bytedeco.opencv.global.opencv_core.addWeighted;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

public class Demo04BlendImages {
    public static void main(String[] args) {
        Mat image = imread("Cookbook/data/boldt.jpg");
        Mat rain = imread("Cookbook/data/rain.png");

        Mat result = new Mat();
        addWeighted(image, 0.7, rain, 0.9, 0.0, result);

        showImage(result);
    }


}
