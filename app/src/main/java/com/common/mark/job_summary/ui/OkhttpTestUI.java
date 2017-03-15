package com.common.mark.job_summary.ui;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.common.mark.job_summary.R;
import com.common.mark.job_summary.utils.DataContants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Okhttp的使用
 * Created by mark on 2017/3/14.
 */

public class OkhttpTestUI extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = OkhttpTestUI.class.getSimpleName();
    private Button mGetBtn;
    private OkHttpClient mOkHttpClient;
    private TextView mResponseText;
    private Button mPostBtn;
    private Button mUploadBtn;
    private Button mDownloadBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //edittext默认不显示软键盘，只有edittext被点击时，软键盘才弹出
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.ui_okhttptest);

        initView();

        mGetBtn.setOnClickListener(this);
        mPostBtn.setOnClickListener(this);
        mUploadBtn.setOnClickListener(this);
        mDownloadBtn.setOnClickListener(this);

    }

    /**
     * 初始化控件
     */
    private void initView() {
        // get请求的使用
        mResponseText = (TextView) findViewById(R.id.okhttp_text);
        mGetBtn = (Button) findViewById(R.id.btn_okhttp_get);
        mPostBtn = (Button) findViewById(R.id.btn_okhttp_post);
        mUploadBtn = (Button) findViewById(R.id.btn_okhttp_upload);
        mDownloadBtn = (Button) findViewById(R.id.btn_okhttp_download);

        // 创建一个OkHttpClient对象
        mOkHttpClient = new OkHttpClient();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btn_okhttp_get) {
            // get请求
            // 1.创建一个OkHttpClient对象
            // 2.创建一个创建一个Request
            // 3.new call
            // 4.请求加入调度

            Request getRequest = new Request.Builder()
                    .url(DataContants.MAOYAN_API)
                    .build();
            Call call = mOkHttpClient.newCall(getRequest);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i(TAG, "请求失败:" + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String result = response.body().string();
                    Log.i(TAG, "服务器返回的结果为：" + result);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mResponseText.setText("服务器返回的结果:" + "\n" + result);
                        }
                    });
                }
            });

        } else if (id == R.id.btn_okhttp_post) {
            // post请求
            // 1.创建一个OkHttpClient对象
            // 2.添加body
            // 3.创建一个创建一个Request
            // 4.new call
            // 5.请求加入调度
            FormBody body = new FormBody.Builder()
                    .add("username", "Mark")
                    .build();

            Request request = new Request.Builder()
                    .url(DataContants.MAOYAN_API)
                    .post(body)
                    .build();

            Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i(TAG, "请求失败: " + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String result = response.body().string();
                    Log.i(TAG, "服务器返回的数据: " + result);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mResponseText.setText("服务器返回的结果:" + "\n" + result);
                        }
                    });
                }
            });

        } else if (id == R.id.btn_okhttp_upload) {
            // 文件上传
            // 1.创建OkHttpClient对象
            // 2.添加body (文件的和浏览器的参数)
            // 3.创建一个创建一个Request
            // 4.new call
            // 5.请求加入调度

            // 要上传的文件
            File file = new File(Environment.getExternalStorageDirectory(), "baolan.jpg");
            // 添加body  application/octet-stream 表示类型是二进制流，不知文件具体类型
            RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addPart(Headers.of(
                            "Content-Disposition",
                            "form-data; name=\"username\""),
                            RequestBody.create(null, "***"))
                    .addPart(Headers.of(
                            "Content-Disposition",
                            "form-data; name=\"mFile\";filename=\"baolan.jpg\""), fileBody)
                    .build();
            Request request = new Request.Builder()
                    .url("http://192.168.31.80:8080/")
                    .post(requestBody)
                    .build();
            Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    // 上传失败
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    // 上传成功
                    Log.i(TAG, response.body().string());
                    Toast.makeText(OkhttpTestUI.this, "上传成功", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (id == R.id.btn_okhttp_download) {
            // 文件下载

            Request request = new Request.Builder()
                    .url("http://img.mingxing.com/upload/attach/2012/12/29341-4AmWzru.jpg")
                    .build();
            Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i(TAG, "下载失败");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.i(TAG, response.isSuccessful() + "");

                    byte[] buff = new byte[2048];
                    int len = 0;

                    InputStream is = response.body().byteStream();
                    File file = new File(Environment.getExternalStorageDirectory() + "/bl.jpg");
                    FileOutputStream fos = new FileOutputStream(file);
                    while ((len = is.read(buff)) != -1) {
                        fos.write(buff, 0, len);
                    }

                    fos.flush();
                    is.close();
                    fos.close();
                }
            });

        }


    }
}
