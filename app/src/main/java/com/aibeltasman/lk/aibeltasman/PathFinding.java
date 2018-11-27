package com.aibeltasman.lk.aibeltasman;

public class PathFinding implements PathFindingIF{

    private ImageRecognition ir;
    private MoveControl mc;
    private SoundUtil sound;
    private boolean targetFound;
    private final int frameTime = 50;      // in millis
    private final int maxRandomTime = 800;

    public PathFinding(MoveControl mc, ImageRecognition ir, SoundUtil sound) {
        this.mc = mc;
        this.ir = ir;
        this.sound = sound;
        this.targetFound = false;
    }

    @Override
    public void drive() {
        while (!this.targetFound) {
            this.driveForward();
        }
        mc.stop();
        sound.playCelebrateSound();
    }

    @Override
    public void driveForward() {
        boolean wayIsClear = this.ir.isClear();

        try {
            if (wayIsClear) {
                this.mc.driveForward();

                while (wayIsClear) {
                    Thread.sleep(this.frameTime);
                    wayIsClear = this.ir.isClear();
                }

                this.mc.stop();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.chooseTurnDirection();
    }

    @Override
    public void chooseTurnDirection() {
        if (this.ir.targetFound()) {
            this.targetFound = true;
        } else {
            if (this.ir.isLeftTurn()) {
                this.turnLeft();
            } else if (this.ir.isRightTurn()) {
                this.turnRight();
            } else {
                this.turnRandomDirection();
            }

            if (this.ir.targetFound()) {
                this.targetFound = true;
            }
        }
    }

    @Override
    public void turnRandomDirection() {
        double direction;
//                 direction == 0 : turn left
//                 direction == 1 : turn right

       direction = Math.floor(Math.random()+0.5);

        if (direction == 0) {
            this.turnLeft();
        } else {
            this.turnRight();
        }
    }

    @Override
    public void turnLeft() {
        boolean wayIsClear = false;
        this.mc.turnLeft();

        try {
            while (!wayIsClear) {
                if (this.ir.targetFound()) {
                    this.targetFound = true;
                    mc.stop();
                    break;
                }
                Thread.sleep(this.frameTime + (int)(Math.random() * maxRandomTime));
                wayIsClear = this.ir.isClear();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.mc.stop();
    }

    @Override
    public void turnRight() {
        boolean wayIsClear = false;
        this.mc.turnRight();

        try {
            while (!wayIsClear) {
                if (this.ir.targetFound()) {
                    this.targetFound = true;
                    mc.stop();
                    break;
                }
                Thread.sleep(this.frameTime + (int)(Math.random() * maxRandomTime));
                wayIsClear = this.ir.isClear();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.mc.stop();
    }
}
