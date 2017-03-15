package com.common.mark.job_summary.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.common.mark.job_summary.R;
import com.common.mark.job_summary.bean.FilmBean;
import com.common.mark.job_summary.utils.BitmapCache;
import com.common.mark.job_summary.utils.DataContants;
import com.common.mark.job_summary.utils.GsonRequest;
import com.common.mark.job_summary.utils.XMLRequest;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

/**
 * Volley的用法
 * Created by mark on 2017/3/13.
 */

public class VolleyTestUI extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = VolleyTestUI.class.getSimpleName();
    private Button mStringRequestBtn;
    private Button mJsonRequestBtn;
    private Button mImgloadBtn;
    private Button mCustomXmlRequestBtn;
    private Button mCustomGsonRequestBtn;
    private Button mSourceBtn;
    private RequestQueue mQueue;
    private EditText mResponseEditText;
    private ImageView mResponseImg;
    private NetworkImageView mNetworkImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //edittext默认不显示软键盘，只有edittext被点击时，软键盘才弹出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        setContentView(R.layout.ui_volleytest);

        initView();

        mStringRequestBtn.setOnClickListener(this);
        mJsonRequestBtn.setOnClickListener(this);
        mImgloadBtn.setOnClickListener(this);
        mCustomXmlRequestBtn.setOnClickListener(this);
        mCustomGsonRequestBtn.setOnClickListener(this);
        mSourceBtn.setOnClickListener(this);

    }

    /**
     * 初始化控件
     */
    private void initView() {
        mStringRequestBtn = (Button) findViewById(R.id.btn_volley_stringrequest);
        mJsonRequestBtn = (Button) findViewById(R.id.btn_volley_jsonrequest);
        mImgloadBtn = (Button) findViewById(R.id.btn_volley_image_load);
        mCustomXmlRequestBtn = (Button) findViewById(R.id.btn_volley_custom_request_xml);
        mCustomGsonRequestBtn = (Button) findViewById(R.id.btn_volley_custom_request_json);
        mSourceBtn = (Button) findViewById(R.id.btn_volley_source);
        mResponseEditText = (EditText) findViewById(R.id.et_volley_response);
        // mResponseImg = (ImageView) findViewById(R.id.iv_volley);

        mNetworkImageView = (NetworkImageView) findViewById(R.id.network_image_view);

        // 创建一个RequestQueue对象
        mQueue = Volley.newRequestQueue(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btn_volley_stringrequest) {
            // StringRequest的用法
            // 1. 创建一个RequestQueue对象。
            // 2. 创建一个StringRequest对象。
            // 3. 将StringRequest对象添加到RequestQueue里面。

            StringRequest stringRequest = new StringRequest(DataContants.MAOYAN_API, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    mResponseEditText.setText(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i(TAG, error.getMessage());
                }
            });

            mQueue.add(stringRequest);

        } else if (id == R.id.btn_volley_jsonrequest) {
            // JsonRequest的用法
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(DataContants.MAOYAN_API, null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            mResponseEditText.setText("这里是服务器返回的json数据: " + "\n" + response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i(TAG, error.getMessage(), error);
                }
            });

            mQueue.add(jsonObjectRequest);

        } else if (id == R.id.btn_volley_image_load) {
            /****************************************
             // ImageRequest的用法
             // 1.创建一个RequestQueue对象
             // 2.创建一个ImageRequest对象
             // 3.将ImageRequest对象添加到RequestQueue中

             ImageRequest imageRequest = new ImageRequest("https://timgsa.baidu.com/" +
             "timg?image&quality=80&size=b9999_10000&sec=1489394484157&di=" +
             "a89cd4bb69c49ec7d823a384005beec4&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150630%2F-7OS-fxemzev2207261.jpg",
             new Response.Listener<Bitmap>() {
            @Override public void onResponse(Bitmap response) {
            mResponseImg.setImageBitmap(response);
            }
            }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError error) {
            Log.i(TAG, error.getMessage(), error);
            mResponseImg.setImageResource(R.drawable.icon);
            }
            });

             mQueue.add(imageRequest);
             ***************************************************************/


            /********************************************************************
             // ImageLoader的用法
             // 1. 创建一个RequestQueue对象。
             // 2. 创建一个ImageLoader对象。
             // 3. 获取一个ImageListener对象。
             // 4. 调用ImageLoader的get()方法加载网络上的图片。

             ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());

             ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(mResponseImg, R.drawable.icon, R.mipmap.ic_launcher_round);

             imageLoader.get("https://timgsa.baidu.com/" +
             "timg?image&quality=80&size=b9999_10000&sec=1489397491044&di=e3ff749ee9ad43a318165865b303912a&" +
             "imgtype=0&src=http%3A%2F%2Fimg1.5sing.kgimg.com%2Fforce%2FT13kEvBCdT1RXrhCrK.jpg", imageListener, 200, 200);
             ***********************************************************************/

            // NetworkImageView的用法
            // 1. 创建一个RequestQueue对象。
            // 2. 创建一个ImageLoader对象。
            // 3. 在布局文件中添加一个NetworkImageView控件。
            // 4. 在代码中获取该控件的实例。
            // 5. 设置要加载的图片地址。

            ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());

            mNetworkImageView.setDefaultImageResId(R.drawable.icon);
            mNetworkImageView.setErrorImageResId(R.mipmap.ic_launcher_round);
            mNetworkImageView.setImageUrl("https://timgsa.baidu.com/" +
                            "timg?image&quality=80&size=b9999_10000&sec=1489397491044&di=e3ff749ee9ad43a318165865b303912a&" +
                            "imgtype=0&src=http%3A%2F%2Fimg1.5sing.kgimg.com%2Fforce%2FT13kEvBCdT1RXrhCrK.jpg",
                    imageLoader);

        } else if (id == R.id.btn_volley_custom_request_xml) {
            // 自定义XmlRequest
            XMLRequest xmlRequest = new XMLRequest("http://flash.weather.com.cn/wmaps/xml/china.xml",
                    new Response.Listener<XmlPullParser>() {
                        @Override
                        public void onResponse(XmlPullParser response) {
                            try {
                                int eventType = response.getEventType();
                                while (eventType != XmlPullParser.END_DOCUMENT) {
                                    switch (eventType) {
                                        case XmlPullParser.START_TAG:
                                            String nodeName = response.getName();
                                            if ("city".equals(nodeName)) {
                                                String pName = response.getAttributeValue(0);
                                                Log.i(TAG, "pName is " + pName);
                                                // mResponseEditText.setText("pName is: " + pName + "\n");
                                                // 返回乱码解决方案

                                            }
                                            break;
                                    }
                                    eventType = response.next();
                                }

                            } catch (XmlPullParserException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i(TAG, error.getMessage(), error);
                }
            });

            mQueue.add(xmlRequest);

        } else if (id == R.id.btn_volley_custom_request_json) {
            // 自定义GsonRequest
            GsonRequest<FilmBean> gsonRequest = new GsonRequest<FilmBean>(DataContants.MAOYAN_API, FilmBean.class,
                    new Response.Listener<FilmBean>() {
                        @Override
                        public void onResponse(FilmBean response) {
                            List<FilmBean.DataBean.MoviesBean> moviesData = response.data.movies;
                            for (int i = 0; i < 20; i++) {
                                Log.i(TAG,moviesData.get(i).nm);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i(TAG, error.getMessage(), error);
                }
            });

            mQueue.add(gsonRequest);
        } else if (id == R.id.btn_volley_source) {

        }
    }

}
