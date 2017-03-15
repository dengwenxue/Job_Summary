package com.common.mark.job_summary.ui;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.renderscript.ScriptGroup;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.common.mark.job_summary.R;
import com.common.mark.job_summary.adapter.MarkBaseAdapter;
import com.common.mark.job_summary.bean.FilmBean;
import com.common.mark.job_summary.utils.CacheUtils;
import com.common.mark.job_summary.utils.DataContants;
import com.common.mark.job_summary.utils.StreamUtils;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 运用HttpURLConnection进行网络访问
 * Created by mark on 2017/3/12.
 */

public class HttpURLConnectionTestUI extends AppCompatActivity {

    private static final String TAG = HttpURLConnectionTestUI.class.getSimpleName();
    private static final int TIME_DELAY = 3000;
    private ListView mListView;
    private List<FilmBean.DataBean.MoviesBean> mData;
    private ProgressDialog mDialog;
    private HttpURLConnection conn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_httpurlconnectiontest);

        mDialog = new ProgressDialog(HttpURLConnectionTestUI.this);

        mDialog.setIcon(R.drawable.icon);
        mDialog.setMessage("正在加载数据...");
        mDialog.setIndeterminate(true);
        mDialog.setCancelable(false);// false,点击屏幕或其他，不会退出对话框

        // 数据准备
        mData = new ArrayList<>();

        // 1.获取缓存
        String cache = CacheUtils.getCache(DataContants.MAOYAN_API, this);

        // 2.判断缓存
        if (!TextUtils.isEmpty(cache)) {
            parseData(cache);
        }

        // 3.没有缓存
        getNetData();


        mListView = (ListView) findViewById(R.id.lv);
        MarkBaseAdapter adapter = new MarkBaseAdapter(this, mData);
        mListView.setAdapter(adapter);

        // 设置分页加载
        // mListView.setOnScrollListener();
    }

    /**
     * 网络获取数据并且进行处理
     */
    private void getNetData() {
        // HttpURLConnect进行网络访问
        // 使用AsyncTask创建线程
        NetTask task = new NetTask();
        task.execute(DataContants.MAOYAN_API);
    }

    /**
     * 处理json数据
     *
     * @param jsonStr
     */
    private void parseData(String jsonStr) {
        Gson gson = new Gson();
        FilmBean bean = gson.fromJson(jsonStr, FilmBean.class);

        mData = bean.data.movies;
    }

    private class NetTask extends AsyncTask<String, Integer, String> {

        /**
         * 这里是最终用户调用Excute时的接口，当任务执行之前开始调用此方法，可以在这里显示进度对话框。
         */
        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute() called");

            mDialog.show();
        }

        /**
         * 后台执行，比较耗时的操作都可以放在这里。
         * 注意这里不能直接操作UI。此方法在后台线程执行，完成任务的主要工作，通常需要较长的时间。
         * 在执行过程中可以调用publicProgress(Progress…)来更新任务的进度。
         *
         * @param params
         * @return
         */
        @Override
        protected String doInBackground(String... params) {
            Log.i(TAG, "doInBackground(Params... params) called");

            String result = "";

            try {
                URL url = new URL(DataContants.MAOYAN_API);
                conn = (HttpURLConnection) url.openConnection();


                // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
                // http正文内，因此需要设为true, 默认情况下是false;
                // conn.setDoOutput(true);

                // 设置是否从httpUrlConnection读入，默认情况下是true;
                // conn.setDoInput(true);

                // Post 请求不能使用缓存
                // conn.setUseCaches(false);

                // 设定传送的内容类型是可序列化的<a href="http://lib.csdn.net/base/javase" class='replace_word' title="Java SE知识库" target='_blank' style='color:#df3434; font-weight:bold;'>Java</a>对象
                // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
                // conn.setRequestProperty("Content-type", "application/x-java-serialized-object");

                // 设定请求的方法为"POST"，默认是GET
                // conn.setRequestMethod("POST");
                conn.setRequestMethod("GET");

                conn.setConnectTimeout(TIME_DELAY);
                conn.setReadTimeout(TIME_DELAY);

                // 连接，上面对urlConn的所有配置必须要在connect之前完成，
                // conn.connect();

                int code = conn.getResponseCode();
                if (code == 200) {
                    // 连接成功
                    // 获取到服务器返回的输入流，并进行读取
                    InputStream is = conn.getInputStream();

                    // 将输入流转化为字符串
                    result = StreamUtils.streamToString(is);

                    // 关闭流
                    is.close();
                    conn.disconnect();

                    mDialog.dismiss();

                    //测试
                    Log.i("发出去的请求:", url.toString());
                    Log.i("读取来的数据:", result);

                    // 设置缓存
                    CacheUtils.setCache(DataContants.MAOYAN_API, result, HttpURLConnectionTestUI.this);

                    // 处理数据
                    parseData(result);

                } else {

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                conn.disconnect();
            }

            return result;
        }

        /**
         * 可以使用进度条增加用户体验度。 此方法在主线程执行，用于显示任务执行的进度。
         *
         * @param values
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.i(TAG, "onProgressUpdate(Progress... progresses) called");
        }

        /**
         * 相当于Handler 处理UI的方式，在这里面可以使用在doInBackground 得到的结果处理操作UI。
         * 此方法在主线程执行，任务执行的结果作为此方法的参数返回
         *
         * @param s
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i(TAG, "onPostExecute(Result result) called");
        }

        /**
         * 用户调用取消时，要做的操作
         */
        @Override
        protected void onCancelled() {
            Log.i(TAG, "onCancelled() called");
            mDialog.dismiss();
        }
    }

}
