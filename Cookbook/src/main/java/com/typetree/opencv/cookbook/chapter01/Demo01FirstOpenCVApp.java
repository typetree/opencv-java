package com.typetree.opencv.cookbook.chapter01;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Scalar;

import static com.typetree.opencv.cookbook.OpencvUtil.showImage;
import static org.bytedeco.javacv.Java2DFrameUtils.toBufferedImage;
import static org.bytedeco.opencv.global.opencv_core.flip;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;

/**
 * @author amamiya
 */
public class Demo01FirstOpenCVApp {

    public static void main(String[] args) {

        Mat image0 = imread("Cookbook/data/boldt.jpg");
        if(image0.empty()){
            System.out.println("Error reading image");
            return;
        }

        showImage(image0);

        Mat image1 = imread("Cookbook/data/boldt.jpg", IMREAD_GRAYSCALE);
        showImage(image1);
        imwrite("Cookbook/data/boldt_gray.jpg", image1);

        Mat puppy = imread("Cookbook/data/puppy.bmp");
        //翻转图片
        Mat image_flip = new Mat();
        flip(puppy, image_flip,0);
        showImage(image_flip);

        Mat image_circle = puppy.clone();
        circle(image_circle, new Point(155,100), 65, Scalar.BLACK, 3, 8, 0);
        showImage(image_circle);

        putText(image_circle, "This is a dog", new Point(40,200), FONT_HERSHEY_PLAIN, 2.0, Scalar.WHITE, 2, 8, false);
        showImage(image_circle);
    }



}
