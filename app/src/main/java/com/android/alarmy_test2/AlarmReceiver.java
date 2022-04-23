package com.android.alarmy_test2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("In AlarmReceiver","Hello!");
        Intent musicIntent = new Intent(context,MusicAlarm.class);
        context.startService(musicIntent);
    }
}
