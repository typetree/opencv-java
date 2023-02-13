package com.typetree.opencv.cookbook.chapter03;

import lombok.Data;
import org.bytedeco.javacpp.indexer.Indexer;
import org.bytedeco.javacpp.indexer.UByteIndexer;
import org.bytedeco.javacpp.indexer.UByteRawIndexer;
import org.bytedeco.opencv.opencv_core.Mat;

import static org.bytedeco.opencv.global.opencv_core.CV_8U;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2Lab;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;

public class ColorDetectorLab {

    public static Mat process(Mat rgbImage, ColorLab targetColor, Integer minDist){
        Mat labImage = new Mat();
        cvtColor(rgbImage, labImage, COLOR_BGR2Lab);

        UByteRawIndexer indexer = labImage.createIndexer();

        Mat dest = new Mat(labImage.rows(), labImage.cols(), CV_8U);
        UByteIndexer destIndexer = dest.createIndexer();

        for (int r = 0; r < labImage.rows(); r++) {
            for (int c = 0; c < labImage.cols(); c++) {
                int v = distance(colorAt(indexer, r, c), targetColor) < minDist ? 255 : 0;
                destIndexer.put(r,c,v);
            }
        }

        return dest;
    }

    private static Double distance(Triple color, ColorLab targetColor) {
        return Math.abs(targetColor.getLAsUInt8() - color.getL())/255d * 100d
                +Math.abs(targetColor.getAAsUInt8() - color.getA())
                +Math.abs(targetColor.getBAsUInt8() - color.getB());
    }

    private static Triple colorAt(UByteRawIndexer indexer, int r, int c) {
        return new Triple(indexer.get(r,c,0), indexer.get(r,c,1), indexer.get(r,c,2));
    }

    public static void main(String[] args) {

    }

    @Data
    private static class Triple {
        private int l;
        private int a;
        private int b;
        public Triple(int l, int a, int b) {
            this.l = l;
            this.a = a;
            this.b = b;
        }
    }
}
