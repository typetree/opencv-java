package com.typetree.opencv.cookbook;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Scalar;

import javax.swing.*;
import java.awt.image.BufferedImage;

import static org.bytedeco.javacv.Java2DFrameUtils.toBufferedImage;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;

public class OpencvUtil {

    public static void showImage(Mat image) {
        BufferedImage bi = toBufferedImage(image);
        showImage(bi);
    }

    public static void showImage(BufferedImage image) {
        CanvasFrame canvasFrame = new CanvasFrame("My Image", 1);

        canvasFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        canvasFrame.showImage(image);
    }

    public static Mat drawOnImage(Mat image, Rect overlay, Scalar color) {
        Mat dest = image.clone();
        rectangle(dest, overlay, color);
        return dest;
    }
}
