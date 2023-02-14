package com.typetree.opencv.cookbook.chapter04;

import org.bytedeco.javacpp.indexer.Indexer;
import org.bytedeco.javacpp.indexer.UByteIndexer;
import org.bytedeco.opencv.opencv_core.Mat;

import static com.typetree.opencv.cookbook.OpencvUtil.showImage;
import static org.bytedeco.opencv.global.opencv_core.CV_8U;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

public class Demo04InvertLut {

    public static Mat invertLut(Mat image){
        int dim = 256;
        Mat lut = new Mat(1, dim, CV_8U);
        UByteIndexer indexer = lut.createIndexer();
        for (int i = 0; i < dim; i++) {
            indexer.put(i, dim-1-i);
        }
        Mat dest = Histogram1D.applyLookUp(image, lut);
        return dest;
    }
    public static void main(String[] args) {
        Mat image = imread("Cookbook/data/group.jpg", IMREAD_GRAYSCALE);
        Mat lut = invertLut(image);
        showImage(lut);
    }
}
