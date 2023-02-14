package com.typetree.opencv.cookbook.chapter04;

import com.sun.marlin.stats.Histogram;
import org.bytedeco.opencv.opencv_core.Mat;

import java.util.Arrays;

import static com.typetree.opencv.cookbook.OpencvUtil.showImage;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

public class Demo01ComputeHistogram {

    public static void main(String[] args) {
        Mat image = imread("Cookbook/data/group.jpg");
        computeHistogram(image);
        showImage(image);
    }

    public static void computeHistogram(Mat image){

        Histogram1D histogram1D = new Histogram1D();

        Float[] histogram = histogram1D.getHistogramAsArray(image);

        for (int i = 0; i < histogram.length; i++) {
            System.out.println(i+":"+histogram[i]);
        }

        long numberOfPixels = image.cols() * image.rows();
        System.out.println("Number of pixels    :"+numberOfPixels);

        long sumOfHistogramBins = Math.round(Arrays.stream(histogram).mapToDouble(a -> a).sum());
        System.out.println("Sum of histogram bins:"+sumOfHistogramBins);

        if(numberOfPixels != sumOfHistogramBins){
            System.out.println("number of pixels must be equals the sum of histogram bins");
        }
    }
}
