package com.common.mark.job_summary.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.common.mark.job_summary.R;

/**
 * 隐式意图的方法启动系统短信
 * 简单概括就是： 意图包括：Action（动作），Category（附加信息），Data（数据，具体内容），Tpye(类型)等等，举个例子，
 * 说白了意图就是启动一个组件的的完整的动作信息,
 * 就像打人，打就是Action动作，人就是Data内容，而Type就是类型，打什么人呢？打坏人，type就是坏指的类型
 * 只有这些信息全了才能执行一个完整的意图
 * 当然还有一些信息，比如scheme就是URI类型的数据的前缀，就像这个例子当中的sms:，还有host主机名，path路径等
 * <p>
 * <p>
 * <p>
 * Created by mark on 2017/3/5.
 */

public class IntentTestUI extends Activity implements View.OnClickListener {

    private Button mSmsBtn1;
    private Button mSmsBtn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_intenttest);

        setupBtn();
    }

    /**
     * Button的动作
     */
    private void setupBtn() {
        mSmsBtn1 = (Button) findViewById(R.id.btn_10086_sms);
        mSmsBtn2 = (Button) findViewById(R.id.btn_myintent);

        mSmsBtn1.setOnClickListener(this);
        mSmsBtn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btn_10086_sms) {
            // 隐式意图的方法启动系统短信

            Intent inetnt = new Intent();
            inetnt.setAction("android.intent.action.SENDTO");// 发送信息的动作
            inetnt.addCategory("android.intent.category.DEFAULT");// 附加信息,可以不加该句，系统默认
            inetnt.setData(Uri.parse("sms:10086"));// 具体的数据,发送给10086
            inetnt.putExtra("sms_body", "别紧张，这仅仅是是一个测试！OY");// 要发送的短信的具体内容
            startActivity(inetnt);

        } else if (id == R.id.btn_myintent) {
            // 自定义隐式意图,打开配置文件中，该<intent-filter>所属的<activity>
            Intent intent = new Intent();
            intent.setAction("net.mark.cn");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setDataAndType(Uri.parse("mark://www.baidu.com/sms"),
                    "sms/send");
            startActivity(intent);
        }
    }
}
