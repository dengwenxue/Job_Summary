package com.common.mark.job_summary.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


/**
 * 广播接收者的测试
 * Created by mark on 2017/3/7.
 */

public class TestBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = TestBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        Log.i(TAG, msg);
    }
}
