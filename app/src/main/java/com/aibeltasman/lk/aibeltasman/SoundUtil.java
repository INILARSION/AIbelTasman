package com.aibeltasman.lk.aibeltasman;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;

/**
 * This class is responsible for playing and pausing sounds.
 */

public class SoundUtil implements SoundUtilIF {

    private MainActivity activity;
    private SoundPool engine;
    private boolean engineLoaded = false;
    private MediaPlayer brake;
    private MediaPlayer celebrate;
    private MediaPlayer failure;

    private static final int STREAM_ID = 1;
    private static final String RESOURCE_PATH = "android.resource://";


    SoundUtil(MainActivity activity){
        this.activity = activity;
        engine = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        engine.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                engine.play(STREAM_ID,1,1,1,-1,1);
            }
        });
        brake = MediaPlayer.create(activity, Uri.parse(RESOURCE_PATH +activity.getPackageName()+"/raw/brakesound"));
        celebrate = MediaPlayer.create(activity, Uri.parse(RESOURCE_PATH +activity.getPackageName()+"/raw/celebratesound"));
        failure = MediaPlayer.create(activity, Uri.parse(RESOURCE_PATH +activity.getPackageName()+"/raw/failsound"));
    }



    @Override
    public void playEngineSound() {
        if(!engineLoaded){
            engine.load(activity, R.raw.enginesound3,1);
            engineLoaded = true;
        } else {
            engine.resume(STREAM_ID);
        }
    }

    @Override
    public void stopEngineSound() {
        engine.pause(STREAM_ID);
    }

    @Override
    public void playBrakeSound() {
        brake.start();
    }

    @Override
    public void playCelebrateSound() {
        celebrate.start();
    }

    @Override
    public void playFailSound() {
        failure.start();
    }
}
