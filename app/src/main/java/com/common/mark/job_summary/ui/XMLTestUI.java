package com.common.mark.job_summary.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.common.mark.job_summary.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.R.attr.name;
import static android.R.attr.type;


/**
 * XML文件的生成与解析
 * Created by mark on 2017/3/10.
 */

public class XMLTestUI extends AppCompatActivity {

    private static final String TAG = XMLTestUI.class.getSimpleName();
    private Button mSaveBtn;
    private Button mParseBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_xmltest);

        initView();
        initEvent();
    }

    /**
     * 事件的处理
     */
    private void initEvent() {

        // 保存数据
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 将xml文件保存为文件格式
                File file = new File(getFilesDir(), "note.xml");

                // 创建一个Xml序列化器
                XmlSerializer serializer = Xml.newSerializer();
                FileOutputStream os = null;

                try {
                    os = new FileOutputStream(file);
                    serializer.setOutput(os, "utf-8");

                    // 开始写数据
                    serializer.startDocument("utf-8", true);
                    serializer.startTag(null, "note");

                    serializer.startTag(null, "to");
                    serializer.text("George");
                    serializer.endTag(null, "to");

                    serializer.startTag(null, "from");
                    serializer.text("John");
                    serializer.endTag(null, "from");

                    serializer.startTag(null, "heading");
                    serializer.text("Reminder");
                    serializer.endTag(null, "heading");

                    serializer.startTag(null, "body");
                    serializer.text("Don't forget the meeting!");
                    serializer.endTag(null, "body");

                    serializer.endTag(null, "note");
                    serializer.endDocument();

                    Toast.makeText(XMLTestUI.this, "数据保存成功", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        // 解析xml数据
        mParseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XmlPullParser parser = Xml.newPullParser();
                InputStream is = null;
                try {
                    is = getAssets().open("note.xml");
                    parser.setInput(is, "utf-8");

                    int eventType = parser.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        eventType = parser.next();
                        switch (eventType) {
                            case XmlPullParser.START_TAG:

                                if ("to".equals(parser.getName())) {
                                    // to
                                    String to = parser.nextText();
                                    Log.i(TAG, "to:" + to);
                                } else if ("from".equals(parser.getName())) {
                                    // from
                                    String from = parser.nextText();
                                    Log.i(TAG, "from:" + from);
                                } else if ("heading".equals(parser.getName())) {
                                    // heading
                                    String heading = parser.nextText();
                                    Log.i(TAG, "from:" + heading);
                                } else if ("body".equals(parser.getName())) {
                                    // body
                                    String body = parser.nextText();
                                    Log.i(TAG, "from:" + body);
                                }

                                break;

                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Toast.makeText(XMLTestUI.this, "xml文件解析完毕", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 初始化控件
     */
    private void initView() {
        mSaveBtn = (Button) findViewById(R.id.btn_xmltest_save);
        mParseBtn = (Button) findViewById(R.id.btn_xmltest_parse);
    }
}
