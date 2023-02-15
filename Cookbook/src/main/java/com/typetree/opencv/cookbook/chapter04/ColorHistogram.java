package com.typetree.opencv.cookbook.chapter04;

import lombok.Data;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static com.typetree.opencv.cookbook.OpencvUtil.wrapInIntBuffer;
import static org.bytedeco.opencv.global.opencv_core.split;
import static org.bytedeco.opencv.global.opencv_imgproc.*;


@Data
public class ColorHistogram {

    private float _minRange = 0f;

    private float _maxRange = 255f;

    private int numberOfBins = 256;

    private Mat getHistogram(Mat image){

        if(image == null || image.channels()!=3){
            return null;
        }
        Mat hist = new Mat();
        IntPointer intPtrChannels = new IntPointer(0, 1, 2);
        IntPointer intPtrHistSize = new IntPointer(numberOfBins, numberOfBins, numberOfBins);
        float[] histRange = new float[]{_minRange, _maxRange};
        PointerPointer<FloatPointer> ptrPtrHistRange = new PointerPointer<>(histRange, histRange, histRange);

        calcHist(image, 1 , intPtrChannels,
                new Mat(), hist, 3,
                intPtrHistSize, ptrPtrHistRange, true, false
        );
        return hist;
    }


    public Mat getHueHistogram(Mat image){
        return getHueHistogram(image, 0);
    }
    public Mat getHueHistogram(Mat image, int minSaturation){
        if(image == null || image.channels()!=3){
            return null;
        }
        Mat hsvImage = new Mat();
        cvtColor(image, hsvImage, COLOR_BGR2HSV);

        Mat saturationMask = new Mat();
        if(minSaturation >0){
            MatVector hsvChannels = new MatVector();
            split(hsvImage, hsvChannels);
            threshold(hsvChannels.get(1), saturationMask, minSaturation, 255, THRESH_BINARY);
        }

        Mat hist = new Mat();
        FloatBuffer histRanges = FloatBuffer.wrap(new float[]{0f, 180f});
        IntBuffer channels = IntBuffer.wrap(new int[]{0});

        calcHist(hsvImage, 1, channels, saturationMask, hist, 1, wrapInIntBuffer(numberOfBins), histRanges);

        return hist;
    }
}
