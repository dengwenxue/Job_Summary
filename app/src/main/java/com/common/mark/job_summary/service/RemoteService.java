package com.common.mark.job_summary.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.remote.service.IMyAidlInterface;

/**
 * Created by mark on 2017/3/6.
 */

public class RemoteService extends Service {

    private static final String TAG = RemoteService.class.getSimpleName();

    // 实现接口中暴露给客户端的Stub--Stub继承自Binder，它实现了IBinder接口
    private IMyAidlInterface.Stub stub = new IMyAidlInterface.Stub() {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public long Add(long a, long b) throws RemoteException {
            return a + b;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(RemoteService.this, "远程绑定：RemoteService", Toast.LENGTH_SHORT).show();
        return stub;// 在客户端连接服务端时，Stub通过ServiceConnection传递到客户端
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(RemoteService.this, "取消远程绑定", Toast.LENGTH_SHORT).show();
        return false;
    }
}
