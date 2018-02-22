package com.yue.ar.suite;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_features2d;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

import java.util.ArrayDeque;

/**
 * Created by luokangyu on 21/02/2018.
 */

public class FeatureTask implements Runnable{

  public  ArrayDeque<Frame> grayFrameBuffer = null;
    OpenCVFrameConverter.ToMat converter ;
    opencv_features2d.ORB orb;
    opencv_core.KeyPointVector sceneKp ;
    opencv_core.Mat grayMat;
    opencv_core.Mat sceneDesc;





    public FeatureTask(){
        grayFrameBuffer = new ArrayDeque<Frame>(24);
        converter = new OpenCVFrameConverter.ToMat();
        orb = opencv_features2d.ORB.create();
        sceneKp = new opencv_core.KeyPointVector();
        sceneDesc = new opencv_core.Mat();
        grayMat = new opencv_core.Mat();

    }

    @Override
    public void run() {

        makeFeature(grayFrameBuffer.getFirst());

    }

    // make ORB Keypoint and Descriptor
    private void makeFeature(Frame grayFrame){

        grayMat = converter.convert(grayFrame);

        orb.detect(grayMat,sceneKp);
        orb.compute(grayMat,sceneKp,sceneDesc);



    }

}
