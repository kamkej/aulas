package com.example.julio.webjs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by julio on 9/17/15.
 */
public class JSInterface {

    Context mContext;

    JSInterface(Context c) {
        mContext = c;
    }
    @JavascriptInterface
    public void showToast(String message){
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }



}
