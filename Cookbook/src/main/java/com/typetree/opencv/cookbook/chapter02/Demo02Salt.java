package com.typetree.opencv.cookbook.chapter02;

import org.bytedeco.javacpp.indexer.Indexer;
import org.bytedeco.javacpp.indexer.UByteIndexer;
import org.bytedeco.opencv.opencv_core.Mat;

import java.util.Random;

import static com.typetree.opencv.cookbook.OpencvUtil.showImage;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;7777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777

public class Demo02Salt {
    public static void main(String[] args) {
        Mat image = imread("Cookbook/data/boldt.jpg", IMREAD_GRAYSCALE);
        salt(image, 10000);
        showImage(image);
    }

    public static void salt(Mat image, int n){
        int channels = image.channels();

        Random random = new Random();
        UByteIndexer indexer = image.createIndexer();

        for (int i = 0; i < n; i++) {

            int row = random.nextInt(image.rows());
            int col = random.nextInt(image.cols());

            for (int j = 0; j < channels; j++) {
                indexer.put(row, col, j, 255);
            }
        }
    }
}
