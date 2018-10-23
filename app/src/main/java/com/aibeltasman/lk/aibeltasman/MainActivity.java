package com.aibeltasman.lk.aibeltasman;

import android.bluetooth.BluetoothDevice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private BluetoothUtil bluetoothUtil;
    private MoveControl moveControl;
    private MovementStrategyIF movementStrategy;

    /**
     * Invoked on activity creation
     * Perform basic logic which should only happen once in the lifecycle
     * @param savedInstanceState  Bundle object containing the activity's previously saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // call the super class onCreate to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);

        // set the user interface layout for this activity
        // the layout file is defined in the project res/layout/main_activity.xml file
        setContentView(R.layout.activity_start);

        bluetoothUtil = new BluetoothUtil(this);
        try {
            connectBluetoothToNxt();
        }catch (Exception e){
            Toast.makeText(this, "Could not connect to NXT!", Toast.LENGTH_LONG).show();
        }


        moveControl = new MoveControl(bluetoothUtil);

    }



    /**
     * Invoked when the activity enters the Started state -> after Created or Stop state
     * Initialize code for maintaining the UI
     *
     * The buttons for choosing the drive mode are set here
     */
    @Override
    protected void onStart() {
        super.onStart();

        Button btnManualCtrl = findViewById(R.id.btnManualCtrl);
        Button btnDriveSquare = findViewById(R.id.btnDriveSqr);
        Button btnFindGreen = findViewById(R.id.btnFindGreen);

        btnManualCtrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupManualCtrl();
            }
        });

        btnDriveSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupDriveSquare();
            }
        });

        btnFindGreen
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupFindGreen();
            }
        });

    }



    /**
     * Invoked after Start state or after Pause state
     * Implements code for interacting  with the user
     * Reinitialize components which Pause state released
     * State stays until something takes focus away from this app
     */
    @Override
    protected void onResume() {
        super.onResume();

    }



    /**
     * Invoked when an interruptive event occurs i.e. activity is not in foreground
     * Release components which are not needed while on Pause
     */
    @Override
    protected void onPause() {
        super.onPause();
    }



    /**
     * Invoked when activity no longer visible and/or activity is  about to be terminated
     * Should release or adjust resources that are not needed while the app is not visible
     * Should contain CPU intensive shutdown operations
     */
    @Override
    protected void onStop() {
        super.onStop();
    }



    /**
     * Invoked before activity is destroyed -> activity finished or temporal destroying the activity due to a configuration change
     * Should release all resources that have not yet been released
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    /**
     * This method connects to bluetooth device with name: "NXT-Test"
     * Value is HARDCODED for now!
     * The device HAS to be paired with the device
     */
    void connectBluetoothToNxt(){
        String[] btnames = bluetoothUtil.getPairedDevicesNames();
        BluetoothDevice btDevice = null;
        for(int i = 0; i< btnames.length; i++){
            if(btnames[i].equals("NXT-Test")){
                btDevice = bluetoothUtil.getPairedDevices()[i];
            }
        }

        bluetoothUtil.connectToDevice(btDevice);
    }


    /**
     * This method sets up the buttons for manual control
     * It uses the MoveControl class for sending the commands
     */
    protected void setupManualCtrl(){
        setContentView(R.layout.activity_manual_control);
        Button forward = findViewById(R.id.btnForward);
        Button left = findViewById(R.id.btnLeft);
        Button right = findViewById(R.id.btnRight);
        Button turnLeft = findViewById(R.id.btnTurnLeft);
        Button turnRight = findViewById(R.id.btnTurnRight);

        forward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()== MotionEvent.ACTION_DOWN) {
                        moveControl.driveForward();
                    return true;

                }
                else if(event.getAction()== MotionEvent.ACTION_UP){
                        moveControl.stop();
                    return true;
                }
                return false;
            }
        });

        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()== MotionEvent.ACTION_DOWN) {
                    moveControl.driveLeft();
                    return true;

                }
                else if(event.getAction()== MotionEvent.ACTION_UP){
                    moveControl.stop();
                    return true;
                }
                return false;
            }
        });

        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()== MotionEvent.ACTION_DOWN) {
                    moveControl.driveRight();
                    return true;

                }
                else if(event.getAction()== MotionEvent.ACTION_UP){
                    moveControl.stop();
                    return true;
                }
                return false;
            }
        });

        turnLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()== MotionEvent.ACTION_DOWN) {
                    moveControl.turnLeft();
                    return true;

                }
                else if(event.getAction()== MotionEvent.ACTION_UP){
                    moveControl.stop();
                    return true;
                }
                return false;
            }
        });

        turnRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()== MotionEvent.ACTION_DOWN) {
                    moveControl.turnRight();
                    return true;

                }
                else if(event.getAction()== MotionEvent.ACTION_UP){
                    moveControl.stop();
                    return true;
                }
                return false;
            }
        });

    }


    /**
     * This method sets up anything for the drive a square mode
     */
    protected void setupDriveSquare(){
        //setContentView(R.layout.activity_camera);
        movementStrategy = new DriveSquareStrategy(moveControl);
        movementStrategy.move();
    }


    /**
     * This method sets up anything for the drive a square mode
     */
    protected void setupFindGreen(){
        setContentView(R.layout.activity_camera);
    }


}
