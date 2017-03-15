package com.common.mark.job_summary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.common.mark.job_summary.ui.BroadcastReceiverUI;
import com.common.mark.job_summary.ui.ContentProviderUI;
import com.common.mark.job_summary.ui.CostomView01UI;
import com.common.mark.job_summary.ui.HttpURLConnectionTestUI;
import com.common.mark.job_summary.ui.JsonTestUI;
import com.common.mark.job_summary.ui.OkhttpTestUI;
import com.common.mark.job_summary.ui.RemoteServiceUI;
import com.common.mark.job_summary.ui.CheckBoxTestUI;
import com.common.mark.job_summary.ui.DialogTestUI;
import com.common.mark.job_summary.ui.EditTextTestUI;
import com.common.mark.job_summary.ui.ImageButtonTestUI;
import com.common.mark.job_summary.ui.ImageViewTestUI;
import com.common.mark.job_summary.ui.IntentTestUI;
import com.common.mark.job_summary.ui.ProgressBarTestUI;
import com.common.mark.job_summary.ui.RadioButtonTestUI;
import com.common.mark.job_summary.ui.RetrofitTestUI;
import com.common.mark.job_summary.ui.ServiceTestUI;
import com.common.mark.job_summary.ui.TextViewTestUI;
import com.common.mark.job_summary.ui.VolleyTestUI;
import com.common.mark.job_summary.ui.XMLTestUI;

public class MainActivity extends AppCompatActivity {

    private int param = 1;
    private static final String TAG = "MainActivity";
    private Button mEditTextBtn;

    //Activity创建时被调用
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // activity 的生命周期测试
        Log.i(TAG, "onCreate called");

        // EditText Button
        setupEditTextBtn();
    }

    /**
     * EditText Button
     */
    private void setupEditTextBtn() {
        mEditTextBtn = (Button) findViewById(R.id.btn_edittext);
        mEditTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditTextTestUI.class);
                startActivity(intent);
            }
        });
    }

    /**
     * TextView
     *
     * @param view
     */
    public void textview(View view) {
        Intent intent = new Intent(this, TextViewTestUI.class);
        startActivity(intent);
    }

    /**
     * ImageButton
     *
     * @param view
     */
    public void imagebutton(View view) {
        Intent intent = new Intent(this, ImageButtonTestUI.class);
        startActivity(intent);
    }

    /**
     * RadioButton与RadioGroup
     *
     * @param view
     */
    public void radiobutton(View view) {
        Intent intent = new Intent(this, RadioButtonTestUI.class);
        startActivity(intent);
    }

    /**
     * CheckBox
     *
     * @param view
     */
    public void checkbox(View view) {
        Intent intent = new Intent(this, CheckBoxTestUI.class);
        startActivity(intent);
    }

    /**
     * ProgressBar
     *
     * @param view
     */
    public void progressbar(View view) {
        Intent intent = new Intent(this, ProgressBarTestUI.class);
        startActivity(intent);
    }

    /**
     * ImageView
     *
     * @param view
     */
    public void img(View view) {
        Intent intent = new Intent(this, ImageViewTestUI.class);
        startActivity(intent);
    }

    /**
     * Dialog
     *
     * @param view
     */
    public void dialog(View view) {
        Intent intent = new Intent(this, DialogTestUI.class);
        startActivity(intent);
    }

    /**
     * 意图Intent
     *
     * @param view
     */
    public void intent(View view) {
        Intent intent = new Intent(this, IntentTestUI.class);
        startActivity(intent);
    }

    /**
     * Service的生命周期
     *
     * @param view
     */
    public void service(View view) {
        Intent intent = new Intent(this, ServiceTestUI.class);
        startActivity(intent);
    }

    /**
     * 远程服务RemoteService
     *
     * @param view
     */
    public void remoteservice(View view) {
        Intent intent = new Intent(this, RemoteServiceUI.class);
        startActivity(intent);
    }

    /**
     * 广播接收者 BroadcastReceiver
     *
     * @param view
     */
    public void broadcastreceiver(View view) {
        Intent intent = new Intent(this, BroadcastReceiverUI.class);
        startActivity(intent);
    }

    /**
     * 内容提供者ContentProvider
     *
     * @param view
     */
    public void contentprovider(View view) {
        Intent intent = new Intent(this, ContentProviderUI.class);
        startActivity(intent);
    }

    /**
     * 自定义View(一)
     *
     * @param v
     */
    public void viewone(View v) {
        Intent intent = new Intent(this, CostomView01UI.class);
        startActivity(intent);
    }

    /**
     * XML文件的生产与解析
     *
     * @param view
     */
    public void xml(View view) {
        Intent intent = new Intent(this, XMLTestUI.class);
        startActivity(intent);
    }

    /**
     * json数据的解析
     *
     * @param view
     */
    public void json(View view) {
        Intent intent = new Intent(this, JsonTestUI.class);
        startActivity(intent);
    }

    /**
     * httpurlconnection进行数据的网络访问
     *
     * @param view
     */
    public void httpbase(View view) {
        Intent intent = new Intent(this, HttpURLConnectionTestUI.class);
        startActivity(intent);
    }

    /**
     * Volley的用法
     *
     * @param view
     */
    public void volleyuseage(View view) {
        Intent intent = new Intent(this, VolleyTestUI.class);
        startActivity(intent);
    }

    /**
     * Okhttp的用法
     *
     * @param view
     */
    public void okhttpuseage(View view) {
        Intent intent = new Intent(this, OkhttpTestUI.class);
        startActivity(intent);
    }

    /**
     * Retrofit的用法
     *
     * @param view
     */
    public void retrofituseage(View view) {
        Intent intent = new Intent(this, RetrofitTestUI.class);
        startActivity(intent);
    }

    /********************* Activity的生命周期方法调用 ************************/

    // Activity创建或者从后台重新回到前台时被调用
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart called");
    }

    // Activity从后台重新回到前台时被调用
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onReStart called");
    }

    // Activity创建或者从被覆盖、后台重新回到前台时被调用
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume called");
    }

    // Activity窗口获得或失去焦点时被调用,在onResume之后或onPause之后
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.i(TAG, "onWindowFocusChanged called");
    }

    // Activity被覆盖到下面或者锁屏时被调用
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause called");
    }

    // 退出当前Activity或者跳转到新Activity时被调用
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop called");
    }

    // 退出当前Activity时被调用,调用之后Activity就结束了
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy called");
    }

    /**
     * Activity被系统杀死时被调用.
     * 例如:屏幕方向改变时,Activity被销毁再重建;当前Activity处于后台,系统资源紧张将其杀死.
     * 另外,当跳转到其他Activity或者按Home键回到主屏时该方法也会被调用,系统是为了保存当前View组件的状态.
     * 在onPause之前被调用.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("param", param);
        Log.i(TAG, "onSaveInstanceState called. put param: " + param);
        super.onSaveInstanceState(outState);
    }

    /**
     * Activity被系统杀死后再重建时被调用.
     * 例如:屏幕方向改变时,Activity被销毁再重建;当前Activity处于后台,系统资源紧张将其杀死,用户又启动该Activity.
     * 这两种情况下onRestoreInstanceState都会被调用,在onStart之后.
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        param = savedInstanceState.getInt("param");
        Log.i(TAG, "onRestoreInstanceState called. get param: " + param);
        super.onRestoreInstanceState(savedInstanceState);
    }
}
