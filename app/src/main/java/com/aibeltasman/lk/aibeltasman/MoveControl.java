package com.aibeltasman.lk.aibeltasman;

/**
 * This class is responsible for sending move commands to the robo.
 */

public class MoveControl implements MoveControlIF {

    private SendRcvIF sendRcv;

    // Add static final byte[] here!

    MoveControl(SendRcvIF sendRecv){
        this.sendRcv = sendRecv;
    }

    @Override
    public void driveForward() {

    }

    @Override
    public void driveLeft() {

    }

    @Override
    public void driveRight() {

    }

    @Override
    public void turnLeft() {

    }

    @Override
    public void turnRight() {

    }

    @Override
    public void stop() {

    }
}
