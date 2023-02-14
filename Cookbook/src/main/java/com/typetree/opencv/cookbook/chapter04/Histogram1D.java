package com.typetree.opencv.cookbook.chapter04;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.indexer.FloatRawIndexer;
import org.bytedeco.javacpp.indexer.Indexer;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import scala.Unit;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Comparator;

import static org.bytedeco.opencv.global.opencv_core.LUT;
import static org.bytedeco.opencv.global.opencv_imgproc.calcHist;
import static org.bytedeco.opencv.global.opencv_imgproc.equalizeHist;

public class Histogram1D {

    private Integer numberOfBins = 256;

    private IntPointer channels = wrapInIntPointer(0);

    private Float _minRange = 0.0f;

    private Float _maxRange = 255f;

    public static Mat applyLookUp(Mat image, Mat lut) {
        Mat dest = new Mat();
        LUT(image, lut, dest);
        return dest;
    }

    public static Mat equalize(Mat image) {
        Mat dest = new Mat();
        equalizeHist(image, dest);
        return dest;
    }

    public BufferedImage getHistogramImage(Mat image){
        Integer width = numberOfBins;
        Integer height = numberOfBins;

        Float[] hist = getHistogramAsArray(image);
        Float histMax = Arrays.stream(hist).max(Comparator.naturalOrder()).get();
        var scale = 0.9/histMax * height;
        BufferedImage canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = canvas.createGraphics();

        graphics.setPaint(Color.WHITE);
        graphics.fillRect(0,0,width,height);

        graphics.setPaint(Color.BLUE);
        for (Integer i = 0; i < numberOfBins; i++) {
            Integer h = Math.round((float)(hist[i] * scale));
            graphics.drawLine(i, height-1, i, height-h-1);
        }

        graphics.dispose();
        return canvas;
    }

    public Float[] getHistogramAsArray(Mat image){
        Mat hist = getHistogram(image, new Mat());
        Float[] dest = new Float[numberOfBins];
        FloatRawIndexer indexer = hist.createIndexer();
        for (Integer bin = 0; bin < numberOfBins; bin++) {
            dest[bin] = indexer.get(bin);
        }
        return dest;
    }
    public Mat getHistogram(Mat image, Mat mask){
        IntPointer histSize = wrapInIntPointer(numberOfBins);
        FloatPointer ranges = new FloatPointer(_minRange, _maxRange);
        Mat hist = new Mat();
        calcHist(wrapInMatVector(image), channels, mask, hist, histSize, ranges);
        return hist;
    }

    private MatVector wrapInMatVector(Mat image) {
        return new MatVector(image);
    }

    private IntPointer wrapInIntPointer(int v) {
        return new IntPointer(1L).put(v);
    }

    public Mat getHistogram(Mat image) {
        return getHistogram(image, new Mat());
    }
}
