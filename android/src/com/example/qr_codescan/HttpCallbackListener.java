package com.example.qr_codescan;

/**
 * Created by phnix on 12/3/2014.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
