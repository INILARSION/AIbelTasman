package com.aibeltasman.lk.aibeltasman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private BluetoothUtil bluetoothUtil;
    private MoveControl moveControl;

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
        moveControl = new MoveControl();

    }



    /**
     * Invoked when the activity enters the Started state -> after Created or Stop state
     * Initialize code for maintaining the UI
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
                setContentView(R.layout.activity_manual_control);
            }
        });

        btnDriveSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_camera);
            }
        });

        btnFindGreen
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_camera);
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


    protected void setupManualCtrl(){

    }


    protected void setupDriveSquare(){

    }


    protected void setupFindGreen(){

    }


}
