package com.aibeltasman.lk.aibeltasman;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

/**
 * This class is responsible for connecting to another (paired) Bluetooth device
 * and for sending and receiving byte-streams.
 */

public class BluetoothUtil implements BluetoothUtilIF, SendRcvIF {

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice[] devices;
    private BluetoothSocket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private MainActivity activity;

    private static final int REQUEST_ENABLE_BT = 200;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    public BluetoothUtil(MainActivity activity) {
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.activity = activity;

        // Turn BluetoothUtil on
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }


    @Override
    public BluetoothDevice[] getPairedDevices() {
        if (devices == null) {
            Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
            devices = new BluetoothDevice[pairedDevices.size()];
            devices = pairedDevices.toArray(devices);
        }
        return devices;
    }


    @Override
    public String[] getPairedDevicesNames() {
        if (devices == null) {
            Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
            devices = new BluetoothDevice[pairedDevices.size()];
            devices = pairedDevices.toArray(devices);
        }
        String[] names = new String[devices.length];

        // There are paired devices. Get the name of each paired device.
        for (int i = 0; i < devices.length; i++) {
            names[i] = devices[i].getName();
        }

        return names;
    }


    @Override
    public void connectToDevice(BluetoothDevice device) {
        try {
            this.socket = device.createRfcommSocketToServiceRecord(MY_UUID);
            this.socket.connect();
            this.outputStream = socket.getOutputStream();
            this.inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("BluetoothUtil.connect",e.getMessage());
            Toast.makeText(activity, "Could not connect: Null BT device", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void sendData(byte[] buffer) {
        try {
            outputStream.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("BT.sendData IO",e.getMessage());
        } catch (NullPointerException e){
            e.printStackTrace();
            Log.e("BT.sendData Null",e.getMessage());
            Toast.makeText(activity, "Could not send: Null BT device", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public byte[] getData(int lenght) {
        byte[] buffer = new byte[lenght];
        try {
            inputStream.read(buffer, 0, lenght);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("BT.getData IO",e.getMessage());
        }catch (NullPointerException e) {
            e.printStackTrace();
            Log.e("BT.getData Null",e.getMessage());
            Toast.makeText(activity, "Could not receive: Null BT device", Toast.LENGTH_SHORT).show();
        }
        return buffer;
    }


    @Override
    public void disconnect() {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("BT.disconct",e.getMessage());
        }
    }

}
