package com.typetree.opencv.cookbook.chapter04;

import lombok.Data;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.opencv.opencv_core.Mat;

import static org.bytedeco.opencv.global.opencv_imgproc.calcBackProject;
import static org.bytedeco.opencv.global.opencv_imgproc.threshold;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY;

@Data
public class ContentFinder {

    private Mat _histogram = new Mat();
    private float _threshold = -1f;

    public Mat find(Mat image){
        int[] channels = {0,1,2};
        return find(image, 0f, 255f, channels);
    }

    public Mat find(Mat image, Float minValue, Float maxValue, int[] channels){
        Mat result = new Mat();

        float[] histRange = {minValue, maxValue};
        IntPointer intPtrChannels = new IntPointer(channels);
        PointerPointer<FloatPointer> ptrPtrHistRange = new PointerPointer<>(histRange, histRange, histRange);
        calcBackProject(image, 1, intPtrChannels, _histogram, result, ptrPtrHistRange, 255, true);

        if(_threshold > 0){
            threshold(result, result, 255*_threshold, 255, THRESH_BINARY);
        }
        return result;
    }
}
