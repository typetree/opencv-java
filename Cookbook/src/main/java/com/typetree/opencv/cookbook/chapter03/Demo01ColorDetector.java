package com.typetree.opencv.cookbook.chapter03;

import org.bytedeco.javacpp.indexer.UByteIndexer;
import org.bytedeco.opencv.global.opencv_core.*;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_core.Mat;

import static com.typetree.opencv.cookbook.OpencvUtil.showImage;
import static org.bytedeco.opencv.global.opencv_core.CV_8U;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

public class Demo01ColorDetector {

    public static Mat ColorDetector(Mat image, ColorRGB target, int minDist){
        UByteIndexer indexer = image.createIndexer();
        Mat dest = new Mat(image.rows(), image.cols(), CV_8U);
        UByteIndexer destIndexer = dest.createIndexer();

        int[] brg = new int[3];
        for (int y = 0; y < image.rows(); y++) {
            for (int x = 0; x < image.cols(); x++) {
                indexer.get(y, x, brg);
                ColorRGB colorRGB = ColorRGB.fromBGR(brg);
                int t = distance(colorRGB,target) < minDist?255:0;
                destIndexer.put(y,x,t);
            }
        }
        return dest;
    }

    private static double distance(ColorRGB colorRGB,ColorRGB target) {
        return Math.abs(target.getRed() - colorRGB.getRed())
                +Math.abs(target.getGreen() - colorRGB.getGreen())
                +Math.abs(target.getBlue() - colorRGB.getBlue());
    }

    public static void main(String[] args) {
        Mat image = imread("Cookbook/data/boldt.jpg");
        ColorRGB colorRGB = new ColorRGB(130, 190, 230);
        Mat mat = ColorDetector(image, colorRGB, 100);
        showImage(image);
        showImage(mat);

    }
}
