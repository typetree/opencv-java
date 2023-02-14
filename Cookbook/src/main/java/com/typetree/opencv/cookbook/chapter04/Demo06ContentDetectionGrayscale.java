package com.typetree.opencv.cookbook.chapter04;

import com.sun.marlin.stats.Histogram;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Scalar;

import static com.typetree.opencv.cookbook.OpencvUtil.drawOnImage;
import static com.typetree.opencv.cookbook.OpencvUtil.showImage;
import static org.bytedeco.opencv.global.opencv_core.CV_8U;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

public class Demo06ContentDetectionGrayscale {

    public static void main(String[] args) {
        Mat image = imread("Cookbook/data/waves.jpg", IMREAD_GRAYSCALE);
        Rect rectROI = new Rect(216, 33, 24, 30);
        Mat drawOnImage = drawOnImage(image, rectROI, new Scalar(1d, 255d, 255d, 0.5));
        showImage(drawOnImage);

        Mat imageRoi = image.apply(rectROI);
        showImage(imageRoi);

        Histogram1D histogram1D = new Histogram1D();
        Mat hist = histogram1D.getHistogram(imageRoi);
        showImage(histogram1D.getHistogramImage(imageRoi));

        ContentFinder contentFinder = new ContentFinder();
        contentFinder.set_histogram(hist);

        Mat result1 = contentFinder.find(image);
        Mat temp = new Mat();

        result1.convertTo(temp, CV_8U, -1, 255);

        showImage(temp);

        contentFinder.set_threshold(0.12f);
        Mat result2 = contentFinder.find(image);
        showImage(result2);
    }


}
