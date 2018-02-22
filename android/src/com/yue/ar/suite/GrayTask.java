package com.yue.ar.suite;

import android.util.Log;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.AndroidFrameConverter;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

import java.util.ArrayDeque;

import static com.yue.ar.suite.CameraSurface.previewHeight;
import static com.yue.ar.suite.CameraSurface.previewWidth;

/**
 * Created by luokangyu on 22/02/2018.
 */

public class GrayTask implements Runnable {


    private static final String TAG = "GrayTask";

    public static boolean isMarker = false;

    public static ArrayDeque<byte[]> previewDataBuffer = null;
    public static ArrayDeque<opencv_core.CvMat> markerBuffer = null;


    AndroidFrameConverter frameConverter = null;
    OpenCVFrameConverter.ToMat matConvter = null;

    byte[] srcData = null;
    Frame srcFrame = null;
    opencv_core.CvMat srcMat = null;


    public GrayTask(){
        if(isMarker) {
            previewDataBuffer = new ArrayDeque<byte[]>();
            frameConverter = new AndroidFrameConverter();
            matConvter = new OpenCVFrameConverter.ToMat();
            srcFrame = new Frame();
            srcMat = new opencv_core.CvMat();

        }else{
            markerBuffer = new ArrayDeque<opencv_core.CvMat>();



        }

    }



    @Override
    public void run() {

        // use boolean to run differ mothod

        if(isMarker){
            Log.d(TAG, "isMarker: true");
            MarkerGrayProcess();

        }else{
            Log.d(TAG, "isMarker: false");
            FrameGrayTask();
        }

    }


    private void MarkerGrayProcess(){




    }


    private void FrameGrayTask(){

        if(!previewDataBuffer.isEmpty()) {
            Log.d(TAG,"previewDataBuffer not empty, process first item");

            srcData  = previewDataBuffer.pollFirst();  // 取得第一項，並從 ArrayDeque 移除
            srcFrame = frameConverter.convert(srcData,previewWidth,previewHeight);
            srcData = null;




        }else{
            Log.d(TAG,"previewDataBuffer is empty.");
        }
    }

}
