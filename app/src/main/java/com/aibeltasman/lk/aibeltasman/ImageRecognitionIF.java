package com.aibeltasman.lk.aibeltasman;

public interface ImageRecognitionIF {

    boolean isClear();          //also calls targetFound() and returns false if targetFound() is true
    boolean isRightTurn();
    boolean isLeftTurn();
    boolean targetFound();
}
