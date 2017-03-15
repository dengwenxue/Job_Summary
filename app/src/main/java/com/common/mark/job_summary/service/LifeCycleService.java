package com.common.mark.job_summary.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.common.mark.job_summary.MainActivity;
import com.common.mark.job_summary.R;


/**
 * Created by mark on 2017/3/5.
 */

public class LifeCycleService extends Service {


    private static final String TAG = LifeCycleService.class.getSimpleName();
    private MyBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCrete executed");
        Log.i(TAG, "LifeCycleService thread id is" + Thread.currentThread().getId());

        int icon = R.mipmap.ic_launcher;
        long when = System.currentTimeMillis();
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(icon)
                .setTicker("Hearty365")
                .setContentTitle("通知标题")
                .setContentText("我是消息")
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent)
                .setContentInfo("Info");
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy executed");
    }

    // 使Service与Activity建立关联
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;

    }

    // 自定义绑定Service，在后台下载任务
    public class MyBinder extends Binder {
        // 模拟后天下载
        public void startDownLoad() {
            Log.i(TAG, "startDownload() executed");
            // 执行具体的下载任务
        }
    }


}
