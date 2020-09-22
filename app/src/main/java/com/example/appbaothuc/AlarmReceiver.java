package com.example.appbaothuc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("toi trong receiver","xin chao");
        String chuoi = intent.getExtras().getString("extra");
        Log.e("Ban Truyen Key",chuoi);
        Intent myIntent = new Intent(context,Music.class);
        myIntent.putExtra("extra",chuoi);
        context.startService(myIntent);
    }
}
