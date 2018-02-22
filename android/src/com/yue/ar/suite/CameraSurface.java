package com.yue.ar.suite;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.util.Size;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static android.content.ContentValues.TAG;

public class CameraSurface extends SurfaceView implements SurfaceHolder.Callback {

    private Camera camera = null;

     static int previewWidth = 0;
     static int previewHeight =0;

    AndroidCameraController cameraController = null;

    public CameraSurface(Context context,AndroidCameraController cameraController) {
        super(context);

        this.cameraController = cameraController;

        getHolder().addCallback(this);
        getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        camera = Camera.open(0);
        camera.setDisplayOrientation(90);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {

        Camera.Parameters params = camera.getParameters();
        camera.setParameters(params);

//        previewWidth = w;
//        previewHeight =h;
//
//        Log.d(TAG,"previewHeight:"+previewHeight);
//        Log.d(TAG,"previewWidth:"+previewWidth);

        try{
            camera.setPreviewCallback(cameraController);
            camera.setPreviewDisplay(holder);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    public Camera getCamera() {
        return camera;
    }


}
