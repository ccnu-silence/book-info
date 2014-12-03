package com.example.qr_codescan;

import android.graphics.Bitmap;

/**
 * Created by phnix on 2014/12/3.
 */
public interface ImageLoadedCallback {
    public void loaded(Bitmap bitMap, String url);
}
