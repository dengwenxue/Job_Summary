package com.common.mark.job_summary.ui;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.common.mark.job_summary.R;
import com.common.mark.job_summary.db.dao.BankDBDao;

import java.util.Random;


/**
 * 建立数据库，以及通过内容观察者来实现对数据库的操作
 * Created by mark on 2017/3/7.
 */

public class ContentProviderUI extends Activity {

    private BankDBDao mDao;
    private Button mCreateDataBtn;
    private Button mQuaryData;
    private Button mAddBtn;
    private Button mDeleteBtn;
    private Button mUpdateBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_contentprovider);

        mDao = new BankDBDao(ContentProviderUI.this);
        final Random random = new Random();

        // 生成数据库
        mCreateDataBtn = (Button) findViewById(R.id.btn_contentprovider_creatdata);
        mCreateDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 20; i++) {
                    mDao.add("邓" + 00 + i, random.nextInt(1000) + random.nextFloat());
                }
            }
        });


        // 查询私有数据库
        mQuaryData = (Button) findViewById(R.id.btn_contentprovider_quary);
        mQuaryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver resolver = getContentResolver();

                Uri uri = Uri.parse("content://com.common.mark.job_summary/account");
                Cursor cursor = resolver.query(uri, null, null, null, null);
                while (cursor.moveToNext()) {
                    String id = cursor.getString(0);
                    String name = cursor.getString(1);
                    String money = cursor.getString(3);
                    System.out.println(id);
                    System.out.println(name);
                    System.out.println(money);
                }
                cursor.close();
            }
        });

        // 向数据库中添加一条数据
        mAddBtn = (Button) findViewById(R.id.btn_contentprovider_add);
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver resolver = getContentResolver();
                Uri uri = Uri.parse("content://com.common.mark.job_summary/account");
                ContentValues values = new ContentValues();
                values.put("name", "邓哈哈");
                values.put("money", "999.009");
                Uri res = resolver.insert(uri, values);

                Toast.makeText(ContentProviderUI.this, res.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // 删除数据库中的信息
        mDeleteBtn = (Button) findViewById(R.id.btn_contentprovider_delete);
        mDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver resolver = getContentResolver();
                Uri uri = Uri.parse("content://com.common.mark.job_summary/account");

                int res = resolver.delete(uri, "name=?", new String[]{"邓哈哈"});
                if (res > 0) {
                    Toast.makeText(ContentProviderUI.this, "刪除成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ContentProviderUI.this, "刪除失敗", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**
         * 更新数据库中的信息
         */
        mUpdateBtn = (Button) findViewById(R.id.btn_contentprovider_update);
        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver resolver = getContentResolver();

                Uri uri = Uri.parse("content://com.common.mark.job_summary/account");
                ContentValues values = new ContentValues();
                values.put("money", 59.9f);
                int res = resolver.update(uri, values, "name=?", new String[]{"邓哈哈"});

                if (res > 0) {
                    Toast.makeText(ContentProviderUI.this, "更新成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ContentProviderUI.this, "更新失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
