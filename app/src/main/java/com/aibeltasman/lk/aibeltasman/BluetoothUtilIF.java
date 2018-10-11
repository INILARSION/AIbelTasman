package com.aibeltasman.lk.aibeltasman;

import android.bluetooth.BluetoothDevice;

public interface BluetoothUtilIF {

    BluetoothDevice[] getPairedDevices();

    String[] getPairedDevicesNames();

    void connectToDevice(BluetoothDevice device);

    void sendData(byte[] buffer);

    byte[] getData(int lenght);

    void disconnect();

}
