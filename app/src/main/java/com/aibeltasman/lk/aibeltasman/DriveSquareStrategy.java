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
        int oneMeterMillis = 3200;
        int halfMeterMillis = 1500;
        int turnTime = 1500;

        try {

            mc.driveForward();
            Thread.sleep(oneMeterMillis);
            mc.stop();

            mc.turnRight();
            Thread.sleep(turnTime);
            mc.stop();

            mc.driveForward();
            Thread.sleep(halfMeterMillis);
            mc.stop();

            mc.turnRight();
            Thread.sleep(turnTime);
            mc.stop();

            mc.driveForward();
            Thread.sleep(oneMeterMillis);
            mc.stop();

            mc.turnRight();
            Thread.sleep(turnTime);
            mc.stop();

            mc.driveForward();
            Thread.sleep(halfMeterMillis);
            mc.stop();

            mc.turnRight();
            Thread.sleep(turnTime);
            mc.stop();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
