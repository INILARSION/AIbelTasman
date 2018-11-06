package com.aibeltasman.lk.aibeltasman;

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

    public ImageRecognition(CameraUtil camera){
        this.camera = camera;
    }

    @Override
    public boolean isClear() {
        return true;
    }

    @Override
    public boolean isRightTurn() {
        return false;
    }

    @Override
    public boolean isLeftTurn() {
        return false;
    }

    @Override
    public boolean targetFound() {
        return false;
    }
}
