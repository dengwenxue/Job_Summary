package com.common.mark.job_summary.ui;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.common.mark.job_summary.R;
import com.common.mark.job_summary.bean.FilmBean;
import com.common.mark.job_summary.utils.CacheUtils;
import com.common.mark.job_summary.utils.DataContants;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mark on 2017/3/10.
 */

public class JsonTestUI extends Activity {

    private static final String TAG = JsonTestUI.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_jsontest);
    }

    /**
     * json数据
     * 解析
     *
     * @param view
     */
    public void jsonParser(View view) {
        /************** 获取网路数据 ********************/
        // 1.获取缓存
        String cache = CacheUtils.getCache(DataContants.MAOYAN_API, JsonTestUI.this);
        // 2.缓存不为空，处理缓存
        if (!TextUtils.isEmpty(cache)) {
            parseData(cache);
        }
        // 3.缓存为空，直接网络获取数据
        getNetData();

    }

    /**
     * Volley获取网络数据
     */
    private void getNetData() {
        // 1.创建一个RequestQue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // 2.创建一个JsonObjectRequest
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(DataContants.MAOYAN_API,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, response + "");

                        // 缓存数据
                        CacheUtils.setCache(DataContants.MAOYAN_API, response + "", JsonTestUI.this);

                        // 业务逻辑，处理数据
                        parseData(response + "");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        });

        // 3.将request放入到requestQueue中
        requestQueue.add(jsonObjectRequest);
    }

    /**
     * 解析json
     *
     * @param jsonStr
     */
    private void parseData(String jsonStr) {
        Gson gson = new Gson();
        FilmBean filmBean = gson.fromJson(jsonStr, FilmBean.class);

        // 将数据存储在ArrayList集合
        List<FilmBean.DataBean.MoviesBean> movieData = new ArrayList<>();
        movieData = filmBean.data.movies;

        for (int i = 0; i < movieData.size(); i++) {
            Log.i(TAG, movieData.get(i).nm);
        }
    }

}
