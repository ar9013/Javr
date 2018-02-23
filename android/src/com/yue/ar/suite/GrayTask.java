package com.yue.ar.suite;


import org.bytedeco.javacv.AndroidFrameConverter;

import org.bytedeco.javacv.OpenCVFrameConverter;

import static com.yue.ar.suite.AndroidCameraController.previewDataBuffer;



/**
 * Created by luokangyu on 23/02/2018.
 */

public class GrayTask implements Runnable {

    private static final String TAG = "GrayTask";



    byte[] src = null;
    static boolean isMarker = false;


    public GrayTask() {


    }

    @Override
    public void run() {

        if(isMarker){
            cvtMarker2Gray();

        }else {
            cvtFrame2Gray();

        }
    }

    private void cvtMarker2Gray(){

    }

    private void cvtFrame2Gray(){

        if(!previewDataBuffer.isEmpty()) {
            src = AndroidCameraController.previewDataBuffer.pollFirst();  // get first item ,and remove from ArrayDeque




        }

    }

}
