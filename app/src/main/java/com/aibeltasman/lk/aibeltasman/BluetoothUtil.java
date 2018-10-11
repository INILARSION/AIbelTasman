package com.aibeltasman.lk.aibeltasman;

import android.bluetooth.BluetoothDevice;

public class BluetoothUtil implements BluetoothUtilIF {



    public BluetoothUtil(){

    }


    @Override
    public BluetoothDevice[] getPairedDevices() {
        return new BluetoothDevice[0];
    }

    @Override
    public String[] getPairedDevicesNames() {
        return new String[0];
    }


    @Override
    public void connectToDevice(BluetoothDevice device) {

    }


    @Override
    public void sendData(byte[] buffer) {

    }


    @Override
    public byte[] getData(int lenght) {
        return new byte[0];
    }


    @Override
    public void disconnect() {

    }

}
