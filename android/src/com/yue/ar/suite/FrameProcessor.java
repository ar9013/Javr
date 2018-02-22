package com.yue.ar.suite;

import android.graphics.YuvImage;
import android.util.Log;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.AndroidFrameConverter;
import org.bytedeco.javacv.Frame;

import java.nio.ByteBuffer;
import java.util.ArrayDeque;

import static com.yue.ar.suite.CameraSurface.previewHeight;
import static com.yue.ar.suite.CameraSurface.previewWidth;
import static org.bytedeco.javacpp.opencv_core.IPL_DEPTH_8U;

/**
 * Created by luokangyu on 21/02/2018.
 */

public class FrameProcessor implements Runnable {

    private static final String TAG = "FrameProcessor";
    public ArrayDeque<byte[]> previewDataBuffer = null;

    AndroidFrameConverter frameConverter = null;
    Frame grayFrame = null;

    FeatureTask featureTask = null;


    byte[] srcframe = null;

    public FrameProcessor(){
        previewDataBuffer = new ArrayDeque<byte[]>(24);
        frameConverter = new AndroidFrameConverter();

        featureTask = new FeatureTask();
        grayFrame = new Frame();

    }

    @Override
    public void run() {

        if(!previewDataBuffer.isEmpty()){

             srcframe  = previewDataBuffer.pollFirst(); // get first and remove from ArrayDeque
            //Log.d(TAG,"frame size: "+frame.length);

            grayFrame = frameConverter.convert(srcframe,previewWidth,previewHeight);
            srcframe = null;

            if(grayFrame.image.length >0) {
                Log.d(TAG, "grayFrameBuffer size :"+featureTask.grayFrameBuffer.size());
                featureTask.grayFrameBuffer.addLast(grayFrame);
            }
            grayFrame= null;

        }else{
            Log.d(TAG,"previewDataBuffer is empty.");
        }
    }


}
