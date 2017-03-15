package com.common.mark.job_summary.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * 银行数据库操作帮助类
 * Created by mark on 2017/3/7.
 */

public class BankOpenHelper extends SQLiteOpenHelper {

    public BankOpenHelper(Context context) {
        super(context, "bank.db", null, 1);
    }

    // 数据库第一次被调用的方法，适合做数据库表结构的初始化
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table account (_id integer primary key autoincrement,name varchar(20),IDNumber varchar(40),money varchar(20))");
    }

    // 数据库版本更新时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
