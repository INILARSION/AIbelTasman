package com.aibeltasman.lk.aibeltasman;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;

/**
 * This class is responsible for playing and pausing sounds.
 */

public class SoundUtil implements SoundUtilIF {

    private SoundPool engine;
    private boolean engineLoaded = false;
    private final int streamID = 1;
    private MediaPlayer brake;
    private MediaPlayer celebrate;
    private MediaPlayer failure;
    MainActivity activity;

    SoundUtil(MainActivity activity){
        this.activity = activity;
        engine = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        engine.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                engine.play(streamID,1,1,1,-1,1);
            }
        });
        brake = MediaPlayer.create(activity, Uri.parse("android.resource://"+activity.getPackageName()+"/raw/brakesound"));
        celebrate = MediaPlayer.create(activity, Uri.parse("android.resource://"+activity.getPackageName()+"/raw/celebratesound"));
        failure = MediaPlayer.create(activity, Uri.parse("android.resource://"+activity.getPackageName()+"/raw/failsound"));
    }



    @Override
    public void playEngineSound() {
        if(!engineLoaded){
            engine.load(activity, R.raw.enginesound3,1);
            engineLoaded = true;
        } else {
            engine.resume(streamID);
        }
    }

    @Override
    public void stopEngineSound() {
        engine.pause(streamID);
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
