package com.aibeltasman.lk.aibeltasman;

/**
 * This class is responsible for sending move commands to the robo.
 */

public class MoveControl implements MoveControlIF {

    private BluetoothUtil sendRcv;
    private SoundUtil sound;

    /*   --- SET OUTPUT MODE ---
     *   Byte 0-1: Command length LSB first.
     *   Byte 2: Command type- direct command. For direct command with response message use 0x00, otherwise, for direct command without the reply message, use 0x00.
     *   Byte 3: Command- set motor output state. Has to be 0x04.
     *   Byte 4: Motor output port. A=0x00,B=0x01,C=0x02,all=0xFF
     *   Byte 5: Motor power set point. -0x64 to 0x64.
     *   Byte 6: Motor mode byte (bit field). 0x01=Turn spec. Motor on,0x02=run/brake instead of run/float, 0x04=turn regulation on
     *   Byte 7: Regulation mode. It is valid only when the motor mode is regulated, otherwise use 0x00 value. 0x00=No regulation,0x01=Power control on spec. Port,0x02=Synchronized wheels
     *   Byte 8: Turn ratio. It is valid only when using a motors synchronization regulation mode, otherwise use 0x00 value. -0x64 to 0x64.
     *   Byte 9: Run state. 0x00=Output will be idle, 0x10=Output will be ramp-up, 0x20=Output will be running, 0x40=Output will be ramp-down
     *   Byte 10-13: Tacho limit LSB first. Valid only when using a ramp-up or ramp-down as a Run state, otherwise use 0x00 value. See explanation for tacho limit below.
     */
    //                                                0     1     2     3            4     5     6     7     8     9     10    11    12    13
    private static final byte[] FORWARD  = new byte[]{0x0C, 0x00, 0x00, 0x04, (byte) 0xFF, 0x40, 0x05, 0x01, 0x32, 0x20, 0x00, 0x00, 0x00, 0x00};

    // Versuch die Räder synchronized laufen zu lassen. Klappt leider so noch nicht!
    private static final byte[] FORWARD2 = new byte[]{0x0C, 0x00, 0x00, 0x04, (byte) 0xFF, 0x64, 0x05, 0x02, 0x64, 0x20, 0x00, 0x00, 0x00, 0x00};

    private static final byte[] STOP     = new byte[]{0x0C, 0x00, 0x00, 0x04, (byte) 0xFF, 0x00, 0x04, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    private static final byte[] B_FULL_FORWARD  = new byte[]{0x0C, 0x00, 0x00, 0x04, (byte) 0x01, 0x40, 0x05, 0x01, 0x32, 0x20, 0x00, 0x00, 0x00, 0x00};
    private static final byte[] B_HALF_FORWARD  = new byte[]{0x0C, 0x00, 0x00, 0x04, (byte) 0x01, 0x20, 0x05, 0x01, 0x32, 0x20, 0x00, 0x00, 0x00, 0x00};
    private static final byte[] C_FULL_FORWARD  = new byte[]{0x0C, 0x00, 0x00, 0x04, (byte) 0x02, 0x40, 0x05, 0x01, 0x32, 0x20, 0x00, 0x00, 0x00, 0x00};
    private static final byte[] C_HALF_FORWARD  = new byte[]{0x0C, 0x00, 0x00, 0x04, (byte) 0x02, 0x20, 0x05, 0x01, 0x32, 0x20, 0x00, 0x00, 0x00, 0x00};

    private static final byte[] B_TURN_FORWARD  = new byte[]{0x0C, 0x00, 0x00, 0x04, (byte) 0x01,  0x20, 0x05, 0x01, 0x32, 0x20, 0x00, 0x00, 0x00, 0x00};
    private static final byte[] C_TURN_FORWARD  = new byte[]{0x0C, 0x00, 0x00, 0x04, (byte) 0x02,  0x20, 0x05, 0x01, 0x32, 0x20, 0x00, 0x00, 0x00, 0x00};
    private static final byte[] B_TURN_BACKWARD = new byte[]{0x0C, 0x00, 0x00, 0x04, (byte) 0x01, -0x20, 0x05, 0x01, 0x32, 0x20, 0x00, 0x00, 0x00, 0x00};
    private static final byte[] C_TURN_BACKWARD = new byte[]{0x0C, 0x00, 0x00, 0x04, (byte) 0x02, -0x20, 0x05, 0x01, 0x32, 0x20, 0x00, 0x00, 0x00, 0x00};

    MoveControl(BluetoothUtil sendRecv, SoundUtil sound){
        this.sendRcv = sendRecv;
        this.sound = sound;
    }

    @Override
    public void driveForward() {
        sendRcv.sendData(FORWARD);
        sound.playEngineSound();
    }

    @Override
    public void driveLeft() {
        sendRcv.sendData(B_FULL_FORWARD);
        sendRcv.sendData(C_HALF_FORWARD);
        sound.playEngineSound();
    }

    @Override
    public void driveRight() {
        sendRcv.sendData(C_FULL_FORWARD);
        sendRcv.sendData(B_HALF_FORWARD);
        sound.playEngineSound();
    }

    @Override
    public void turnLeft() {
        sendRcv.sendData(B_TURN_FORWARD);
        sendRcv.sendData(C_TURN_BACKWARD);
        sound.playEngineSound();
    }

    @Override
    public void turnRight() {
        sendRcv.sendData(B_TURN_BACKWARD);
        sendRcv.sendData(C_TURN_FORWARD);
        sound.playEngineSound();
    }

    @Override
    public void stop() {
        sendRcv.sendData(STOP);
        sound.stopEngineSound();
        sound.playBrakeSound();
    }
}
