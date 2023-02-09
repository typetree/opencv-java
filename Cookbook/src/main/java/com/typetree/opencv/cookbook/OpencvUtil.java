package com.typetree.opencv.cookbook;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;
import java.awt.image.BufferedImage;

import static org.bytedeco.javacv.Java2DFrameUtils.toBufferedImage;

public class OpencvUtil {

    public static void showImage(Mat image) {
        CanvasFrame canvasFrame = new CanvasFrame("My Image", 1);

        canvasFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        BufferedImage bi = toBufferedImage(image);

        canvasFrame.showImage(bi);
    }
}
