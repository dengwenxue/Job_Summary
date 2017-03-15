package com.common.mark.job_summary.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.common.mark.job_summary.R;
import com.common.mark.job_summary.receiver.TestBroadcastReceiver;

/**
 * Created by mark on 2017/3/7.
 */

public class BroadcastReceiverUI extends Activity {

    private Button mSendBroadcast;
    private TestBroadcastReceiver mReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_broadcastreceiver);

        mSendBroadcast = (Button) findViewById(R.id.btn_send);
        mSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 发送广播
                Intent intent = new Intent("android.intent.action.TEST_BROADCAST");
                intent.putExtra("msg", "这是我在BroadcastReceiverUI发送到广播哦，你收到了吗，收到的话就点个赞哦！");
                sendBroadcast(intent);
            }
        });

        // 动态注册广播
//        mReceiver = new TestBroadcastReceiver();
//
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("android.intent.action.TEST_BROADCAST");
//
//        registerReceiver(mReceiver,filter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // unregisterReceiver(mReceiver);
    }
}
