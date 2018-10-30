package com.aibeltasman.lk.aibeltasman;

public class ImageRecognition implements ImageRecognitionIF {

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
