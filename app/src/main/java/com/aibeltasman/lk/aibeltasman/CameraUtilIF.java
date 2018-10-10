package com.aibeltasman.lk.aibeltasman;

import android.graphics.Bitmap;
import android.hardware.Camera;

import java.util.List;

interface CameraUtilIF {

    void closeCamera();

    void startFlashlight();

    void stopFlashlight();

    List<int[]> getPreviewFpsRange();

    List<Integer> getSupportedPreviewFormats();

    List<Camera.Size> getSupportedPreviewSizes();

    Bitmap getPreviewBitmap();

    int getBitmapWidth();

    int getBitmapHeight();

    int getPixel(int x, int y);

    int getRedPixel(int x, int y);

    int getGreenPixel(int x, int y);

    int getBluePixel(int x, int y);

    void setPreviewFormat(int format);

    void setPreviewFpsRange(int min, int max);

    void setPreviewSize(int width, int height);


}
