package com.typetree.opencv.cookbook.chapter02;

import org.bytedeco.javacpp.indexer.ByteRawIndexer;
import org.bytedeco.javacpp.indexer.UByteIndexer;
import org.bytedeco.opencv.opencv_core.Mat;

import static com.typetree.opencv.cookbook.OpencvUtil.showImage;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

public class Demo02ColorReduce {

    public static void main(String[] args) {
        Mat image = imread("Cookbook/data/boldt.jpg");
        colorReduce(image);
        showImage(image);
    }

    private static void colorReduce(Mat image) {
        colorReduce(image, 64);
    }

    private static void colorReduce(Mat image, int div) {
        int nbElements = image.rows() * image.cols() * image.channels();

        UByteIndexer indexer = image.reshape(1, nbElements).createIndexer();
        for (int i = 0; i < nbElements; i++) {
            int v = indexer.get(i);
            //颜色削减公式
            int newV = v / div * div + div / 2;
            indexer.put(i,newV);
        }
    }
}
