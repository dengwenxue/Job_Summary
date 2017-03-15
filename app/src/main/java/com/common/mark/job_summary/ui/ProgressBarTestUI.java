package com.common.mark.job_summary.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.common.mark.job_summary.R;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by mark on 2017/3/3.
 */

public class ProgressBarTestUI extends Activity {

    private static final int PROGRESS = 0;
    private static final int FINISH = 1;
    private ProgressBar mProgressBar1;
    private ProgressBar mProgressBar2;

    //该程序模拟填充长度为100的数组
    private int[] data = new int[100];
    //记录ProgressBar的完成进度
    int status = 0;
    int hasData = 0;

    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_progressbartest);

        mProgressBar1 = (ProgressBar) findViewById(R.id.progressbar1);
        mProgressBar2 = (ProgressBar) findViewById(R.id.progressbar2);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == PROGRESS) {
                    mProgressBar2.setProgress(status);
                } else if (msg.what == FINISH) {
                    Toast.makeText(ProgressBarTestUI.this, "下载完成", Toast.LENGTH_LONG).show();
                }

            }
        };

        // 子线程完成耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (status < 100) {
                    // 获取耗时操作的完成百分比
                    status = doWork();

                    Message message = Message.obtain();
                    message.what = PROGRESS;

                    // 发送消息
                    mHandler.sendMessage(message);
                }

                if (status == 100) {
                    Message message = Message.obtain();
                    message.what = FINISH;

                    // 发送消息
                    mHandler.sendMessage(message);
                }
            }
        }).start();
    }

    /**
     * 获取耗时操作的完成百分比
     *
     * @return hasData
     */
    private int doWork() {
        data[hasData++] = (int) (Math.random() * 100);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return hasData;
    }
}
