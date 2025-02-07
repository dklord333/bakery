package com.example.myapplication.Support_clases;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class ImageUtils {
    public static Bitmap decodeBase64(String base64String) {
        byte[] decodedBytes = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
