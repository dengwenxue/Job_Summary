package com.common.mark.job_summary.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.common.mark.job_summary.R;
import com.common.mark.job_summary.service.LifeCycleService;


/**
 * Created by mark on 2017/3/5.
 */

public class ServiceTestUI extends Activity implements View.OnClickListener {

    private static final String TAG = ServiceTestUI.class.getSimpleName();
    private Button mStartServiceBtn;
    private Button mStopServiceBtn;

    private LifeCycleService.MyBinder myBinder;


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (LifeCycleService.MyBinder) service;
            myBinder.startDownLoad();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private Button mBindServiceBtn;
    private Button mUnbindServiceBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_servicetest);

        Log.i(TAG, "MainActivity thread id is " + Thread.currentThread().getId());

        initView();

        mStartServiceBtn.setOnClickListener(this);
        mStopServiceBtn.setOnClickListener(this);
        mBindServiceBtn.setOnClickListener(this);
        mUnbindServiceBtn.setOnClickListener(this);

    }

    /**
     * 初始化控件
     */
    private void initView() {
        mStartServiceBtn = (Button) findViewById(R.id.btn_start_service);
        mStopServiceBtn = (Button) findViewById(R.id.btn_stop_service);
        mBindServiceBtn = (Button) findViewById(R.id.btn_bind_service);
        mUnbindServiceBtn = (Button) findViewById(R.id.btn_unbind_service);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btn_start_service) {
            Intent startIntent = new Intent(ServiceTestUI.this, LifeCycleService.class);
            startService(startIntent);
        } else if (id == R.id.btn_stop_service) {
            Intent stopIntent = new Intent(ServiceTestUI.this, LifeCycleService.class);
            stopService(stopIntent);
        } else if (id == R.id.btn_bind_service) {
            Intent bindIntent = new Intent(ServiceTestUI.this, LifeCycleService.class);
            bindService(bindIntent, connection, BIND_AUTO_CREATE);
        } else if (id == R.id.btn_unbind_service) {
            unbindService(connection);
        }


    }
}
