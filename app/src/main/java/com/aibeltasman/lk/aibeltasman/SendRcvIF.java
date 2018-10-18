package com.aibeltasman.lk.aibeltasman;

public interface SendRcvIF {

    void sendData(byte[] buffer);

    byte[] getData(int lenght);
}
