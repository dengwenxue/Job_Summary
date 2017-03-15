package com.common.mark.job_summary.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.common.mark.job_summary.service.RemoteService;
import com.remote.service.*;

import com.common.mark.job_summary.R;

/**
 * 远程服务RemoteService
 * Created by mark on 2017/3/6.
 */

public class RemoteServiceUI extends Activity {

    private static final String TAG = RemoteServiceUI.class.getSimpleName();
    private Button mBindServiceBtn;
    private Button mUnbindServiceBtn;
    private ServiceConnection mConn;
    private IMyAidlInterface myAidlInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_remoteservice);

        // 建立与服务的连接
        mConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // 从连接中获取Stub对象
                myAidlInterface = IMyAidlInterface.Stub.asInterface(service);
                // 调用Remote Service提供的方法
                try {
                    Log.i(TAG, "获得消息:" + myAidlInterface.Add(Math.round(Math.random() * 100), Math.round(Math.random() * 100)));
                } catch (RemoteException e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                // 断开绑定连接
                myAidlInterface = null;
            }
        };

        mBindServiceBtn = (Button) findViewById(R.id.btn_bind_remoteservice);
        mUnbindServiceBtn = (Button) findViewById(R.id.btn_unbind_remoteservice);

        // 绑定服务
        mBindServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindRemoteService();
            }
        });

        // 解绑服务
        mUnbindServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(mConn);
            }
        });

    }

    /**
     * 绑定服务
     */
    private void bindRemoteService() {

        // 绑定服务
        Intent intentService = new Intent(RemoteServiceUI.this, RemoteService.class);
        bindService(intentService, mConn, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mConn != null) {
            unbindService(mConn);
        }
    }
}
