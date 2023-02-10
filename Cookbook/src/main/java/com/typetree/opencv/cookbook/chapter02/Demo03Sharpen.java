package com.typetree.opencv.cookbook.chapter02;

import org.bytedeco.javacpp.indexer.FloatIndexer;
import org.bytedeco.javacpp.indexer.Indexer;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Scalar;

import static com.typetree.opencv.cookbook.OpencvUtil.showImage;
import static org.bytedeco.opencv.global.opencv_core.CV_32F;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgproc.filter2D;

public class Demo03Sharpen {

    public static void main(String[] args) {
        Mat image = imread("Cookbook/data/boldt.jpg");
        Mat sharpened = sharpened(image);
        showImage(sharpened);

    }

    public static Mat sharpened(Mat image){
        Mat dest = new Mat();
        Mat kernel = new Mat(3, 3, CV_32F, new Scalar(0));
        FloatIndexer ki = kernel.createIndexer();
        ki.put(1,1,5);
        ki.put(0,1,-1);
        ki.put(2,1,-1);
        ki.put(1,0,-1);
        ki.put(1,2,-1);

        filter2D(image, dest, image.depth(), kernel);
        return dest;
    }

}
