package com.typetree.opencv.cookbook.chapter04;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Scalar;

import static com.typetree.opencv.cookbook.OpencvUtil.drawOnImage;
import static com.typetree.opencv.cookbook.OpencvUtil.showImage;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

public class Demo07ContentDetectionColor {

    public static void main(String[] args) {
        Mat colorImage = imread("Cookbook/data/waves.jpg");

        Rect rect = new Rect(0, 0, 100, 45);

        Mat mat = drawOnImage(colorImage, rect, new Scalar(0d, 255d, 255d, 0.5));
        showImage(mat);

        Mat imageRoi = colorImage.apply(rect);

        ColorHistogram hc = new ColorHistogram();
        hc.setNumberOfBins(8);
        Mat hist = hc.getHistogram(imageRoi);

        ContentFinder contentFinder = new ContentFinder();
        contentFinder.set_histogram(hist);
        contentFinder.set_threshold(0.05f);

        Mat result1 = contentFinder.find(colorImage);
        showImage(result1);
    }
}
