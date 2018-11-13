package com.aibeltasman.lk.aibeltasman;

import android.graphics.Bitmap;
import android.graphics.Color;

public class ImageRecognition implements ImageRecognitionIF {

    /* --- COLOR VALUES ---
    first two from LG G5
    last two from Samsung S9

    Red Tape:   R: 145 G: 36  B: 42
                R: 129 G: 40  B: 46
                R: 136 G: 13  B: 27
                R: 135 G: 21  B: 35

    Green Spot: R: 116 G: 163 B: 124
                R: 118 G: 165 B: 126
                R: 177 G: 217 B: 191
                R: 177 G: 217 B: 191

    Table:      R: 157 G: 154  B: 146
                R: 160 G: 157  B: 149
                R: 215 G: 215  B: 213
                R: 210 G: 210  B: 208

    Floor:      R: 170  G: 174 B: 175
                R: 183  G: 191 B: 190
                R: 129  G: 143 B: 133
                R: 140  G: 145 B: 142
     */

    private CameraUtil camera;
    private int bitmapWidth;
    private int bitmapHeight;

    public ImageRecognition(CameraUtil camera){
        while (!camera.isReady()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.camera = camera;
        bitmapWidth = camera.getBitmapWidth();
        bitmapHeight = camera.getBitmapHeight();
    }

    @Override
    public boolean isClear() {
        Bitmap bitmap = camera.getPreviewBitmap();
        int pixel;

        // toleranz soll false positives abfangen -> falls nicht rote Pixels als Rot erkannt werden
        int toleranz = 5;

        for (int i = 0; i < bitmapWidth; i++) {
            for (int j = 0; j < 70; j++) {
                pixel = bitmap.getPixel(i, (bitmapHeight / 10 * 5)+j);

                if(isRed(pixel) || isGreen(pixel)){
                    --toleranz;
                }

                if(toleranz < 0){
                    return false;
                }
            }

        }

        return true;
    }

    @Override
    public boolean isRightTurn() {
        Bitmap bitmap = camera.getPreviewBitmap();
        int pixel;

        // toleranz soll false positives abfangen -> falls nicht rote Pixels als Rot erkannt werden
        int toleranz = 5;

        for (int i = 0; i < 58; i++) {
            for (int j = 20; j < bitmapHeight; j++) {
                pixel = bitmap.getPixel(i, j);

                if(isRed(pixel)){
                    --toleranz;
                }

                if(toleranz < 0){
                    return true;
                }
            }

        }

        return false;
    }

    @Override
    public boolean isLeftTurn() {
        Bitmap bitmap = camera.getPreviewBitmap();
        int pixel;

        // toleranz soll false positives abfangen -> falls nicht rote Pixels als Rot erkannt werden
        int toleranz = 5;

        for (int i = 118; i < 177; i++) {
            for (int j = 20; j < bitmapHeight; j++) {
                pixel = bitmap.getPixel(i, j);

                if(isRed(pixel)){
                    --toleranz;
                }

                if(toleranz < 0){
                    return true;
                }
            }

        }

        return false;
    }

    @Override
    public boolean targetFound() {
        Bitmap bitmap = camera.getPreviewBitmap();
        int pixel = 0;

        // toleranz soll false positives abfangen -> falls nicht grüne Pixels als Rot erkannt werden
        int toleranz = 5;

        for (int i = 0; i < bitmapWidth; i++) {
            pixel = bitmap.getPixel(i, bitmapHeight / 10 * 9);

            if(isGreen(pixel)){
                --toleranz;
            }

            if(toleranz < 0){
                return true;
            }
        }

        return false;
    }


    private boolean isRed(int pixel){
        int red = Color.red(pixel);
        return (red > 100 && (((red + Color.green(pixel) + Color.blue(pixel)) / 3 )< red - 20));
    }

    private boolean isGreen(int pixel){
        int green = Color.green(pixel);
        return (green > 100 && (((Color.red(pixel) + green + Color.blue(pixel)) / 3 )< green - 15));
    }

    private boolean isBlue(int pixel){
        int blue = Color.blue(pixel);
        return (blue > 100 && (((Color.red(pixel) + Color.green(pixel)+ blue) / 3 )< blue - 10));
    }
}
