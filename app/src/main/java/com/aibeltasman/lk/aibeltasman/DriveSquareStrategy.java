package com.aibeltasman.lk.aibeltasman;

/**
 * This class is the strategy class for driving a square.
 * It will be used in the ComputeMovement class.
 */

public class DriveSquareStrategy implements MovementStrategyIF {

    private MoveControl mc;

    public DriveSquareStrategy(MoveControl mc){
        this.mc = mc;
    }

    public void move(){
        try {
            mc.driveForward();
            Thread.sleep(4000);
            mc.stop();
            mc.turnRight();
            Thread.sleep(550);
            mc.stop();
            mc.driveForward();
            Thread.sleep(2000);
            mc.stop();
            mc.turnRight();
            Thread.sleep(550);
            mc.stop();
            mc.driveForward();
            Thread.sleep(4000);
            mc.stop();
            mc.turnRight();
            Thread.sleep(550);
            mc.stop();
            mc.driveForward();
            Thread.sleep(2000);
            mc.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
