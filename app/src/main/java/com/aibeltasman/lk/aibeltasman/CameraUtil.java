package com.aibeltasman.lk.aibeltasman;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.Log;
import android.view.TextureView;

import java.io.IOException;
import java.util.List;

/**
 * This class is responsible for opening cameras and for getting/setting its parameters.
 * It should also give the camera-preview-frames and its pixels.
 */

public class CameraUtil implements CameraUtilIF {

    private TextureView textureView;
    private Camera camera;
    private boolean cameraReady = false;


    CameraUtil(int cameraID, int displayOrientationDeg, TextureView textureView){
        this.camera = Camera.open(cameraID);
        this.camera.setDisplayOrientation(displayOrientationDeg);
        this.textureView = textureView;
        this.textureView.setSurfaceTextureListener(createSurfaceTextureListener());
    }


    private TextureView.SurfaceTextureListener createSurfaceTextureListener(){
        return new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                try {
                    camera.setDisplayOrientation(90);
                    camera.setPreviewTexture(surface);
                    camera.startPreview();
                    cameraReady = true;
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("camera.previewTex",e.getMessage());
                }
            }


            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                // TextureView will never be resized after initialization.
                // This method will never be called.
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                closeCamera();
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
                // Has to be implemented but will never be used
            }
        };

    }


    @Override
    public void closeCamera() {
        camera.release();
    }


    @Override
    public void startFlashlight() {
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(parameters);
    }


    @Override
    public void stopFlashlight() {
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(parameters);
    }


    // retured FPS is FPS*1000 to fit in int
    @Override
    public List<int[]> getPreviewFpsRange(){
        Camera.Parameters parameters = camera.getParameters();
        List<int[]> fpsRange = parameters.getSupportedPreviewFpsRange();
        return fpsRange;
    }


    // see here for formats: https://developer.android.com/reference/android/graphics/ImageFormat
    @Override
    public List<Integer> getSupportedPreviewFormats(){
        Camera.Parameters parameters = camera.getParameters();
        List<Integer> formats = parameters.getSupportedPreviewFormats();
        return formats;
    }


    @Override
    public List<Camera.Size> getSupportedPreviewSizes() {
        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();
        return sizes;
    }


    @Override
    public Bitmap getPreviewBitmap() {
        return textureView.getBitmap();
    }


    @Override
    public int getBitmapWidth() {
        return textureView.getBitmap().getWidth();
    }


    @Override
    public int getBitmapHeight() {
        return textureView.getBitmap().getHeight();
    }


    @Override
    public int getPixel(int x, int y) {
        try{
            return textureView.getBitmap().getPixel(x, y);
        }
        catch (NullPointerException e){
            e.printStackTrace();
            Log.e("Cam.Pixel", e.getMessage());
            return -1;
        }
    }


    // returns values in range (0 ,255)
    @Override
    public int getRedPixel(int x, int y) {
        try {
            return Color.red(textureView.getBitmap().getPixel(x, y));
        }
        catch (NullPointerException e){
            e.printStackTrace();
            Log.e("Cam.Red", e.getMessage());
            return -1;
        }
    }


    // returns values in range (0 ,255)
    @Override
    public int getGreenPixel(int x, int y) {
        try{
        return Color.green(textureView.getBitmap().getPixel(x, y));
        }
        catch (NullPointerException e){
            e.printStackTrace();
            Log.e("Cam.GReen", e.getMessage());
            return -1;
        }
    }


    // returns values in range (0 ,255)
    @Override
    public int getBluePixel(int x, int y) {
        try {
        return Color.blue(textureView.getBitmap().getPixel(x, y));
        }
        catch (NullPointerException e){
            e.printStackTrace();
            Log.e("Cam.Blue", e.getMessage());
            return -1;
        }
    }


    public boolean isReady(){
        return cameraReady;
    }


    // see here for formats: https://developer.android.com/reference/android/graphics/ImageFormat
    @Override
    public void setPreviewFormat(int format) {
        Camera.Parameters parameters = camera.getParameters();
        parameters.setPreviewFormat(format);
        camera.setParameters(parameters);
    }


    // FPS parameters should be FPS*1000 to fit in int
    @Override
    public void setPreviewFpsRange(int min, int max) {
        Camera.Parameters parameters = camera.getParameters();
        parameters.setPreviewFpsRange(min,max);
        camera.setParameters(parameters);
    }


    @Override
    public void setPreviewSize(int width, int height) {
        Camera.Parameters parameters = camera.getParameters();
        parameters.setPreviewSize(width, height);
        camera.setParameters(parameters);
    }
}
