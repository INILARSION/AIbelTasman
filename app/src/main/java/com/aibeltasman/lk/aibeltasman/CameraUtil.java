package com.aibeltasman.lk.aibeltasman;

import android.graphics.Bitmap;
import android.hardware.Camera;

import java.util.List;

public class CameraUtil implements CameraUtilIF {


    CameraUtil(){

    }


    @Override
    public void closeCamera() {

    }


    @Override
    public void startFlashlight() {

    }


    @Override
    public void stopFlashlight() {

    }


    // retured FPS is FPS*1000 to fit in int
    static List<int[]> getPreviewFpsRange(int cameraID){
        return null;

    }


    // see here for formats: https://developer.android.com/reference/android/graphics/ImageFormat
    static List<Integer> getSupportedPreviewFormats(int cameraID){
        return null;
    }


    static List<Integer> getSupportedPreviewSizes(int cameraID) {
        return null;
    }


    @Override
    public Bitmap getPreviewBitmap() {
        return null;
    }


    @Override
    public int getBitmapWidth() {
        return 0;
    }


    @Override
    public int getBitmapHeight() {
        return 0;
    }


    @Override
    public int getPixel(int x, int y) {
        return 0;
    }


    @Override
    public int getRedPixel(int x, int y) {
        return 0;
    }


    @Override
    public int getGreenPixel(int x, int y) {
        return 0;
    }


    @Override
    public int getBluePixel(int x, int y) {
        return 0;
    }


    // see here for formats: https://developer.android.com/reference/android/graphics/ImageFormat
    @Override
    public void setPreviewFormat(int format) {

    }


    // FPS parameters should be FPS*1000 to fit in int
    @Override
    public void setPreviewFpsRange(int min, int max) {

    }


    @Override
    public void setPreviewSize(int width, int height) {

    }
}
