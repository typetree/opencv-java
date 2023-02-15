package com.typetree.opencv.cookbook.chapter04;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.TermCriteria;

import static com.typetree.opencv.cookbook.OpencvUtil.drawOnImage;
import static com.typetree.opencv.cookbook.OpencvUtil.showImage;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_COLOR;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2HSV;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_video.meanShift;

public class Demo08MeanShiftDetector {

    public static Mat meanShiftImage(Mat imageRoi, Mat targetImage, Rect rect){

        int minSaturation = 65;
        Mat templateHueHist = new ColorHistogram().getHueHistogram(imageRoi, minSaturation);

        ContentFinder contentFinder = new ContentFinder();
        contentFinder.set_histogram(templateHueHist);

        Mat hsvTargetImage = new Mat();
        cvtColor(targetImage, hsvTargetImage, COLOR_BGR2HSV);

        contentFinder.set_threshold(-1f);
        Mat hueBackProjectionImage = contentFinder.find(hsvTargetImage, 0f, 180f, new int[]{0});

        showImage(hueBackProjectionImage);

        TermCriteria criteria = new TermCriteria(TermCriteria.MAX_ITER, 10, 0.01);
        int r = meanShift(hueBackProjectionImage, rect, criteria);
        System.out.println("meanshift = "+r);

        return drawOnImage(targetImage, rect, Scalar.RED);
    }

    private static Mat getImageRoi(Mat templateImage, Rect rect) {
        Scalar red = new Scalar(0, 0, 255, 128);
        Mat mat0 = drawOnImage(templateImage, rect, red);
        showImage(mat0);

        Mat imageRoi = templateImage.apply(rect);
        return imageRoi;
    }

    public static void main(String[] args) {

        Rect rect = new Rect(110, 260, 35, 40);

        Mat templateImage = imread("Cookbook/data/baboon1.jpg", IMREAD_COLOR);
        Mat imageRoi = getImageRoi(templateImage, rect);
        showImage(imageRoi);

        Mat targetImage = imread("Cookbook/data/baboon3.jpg", IMREAD_COLOR);
        Mat result = meanShiftImage(imageRoi, targetImage, rect);
        showImage(result);

    }
}
