package com.iibtech.ionic.intent;

import android.util.Log;

public class IntentReceieved {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }
}
