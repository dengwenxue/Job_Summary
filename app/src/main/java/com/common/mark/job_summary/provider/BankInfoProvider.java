package com.common.mark.job_summary.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.common.mark.job_summary.db.BankOpenHelper;

/**
 * 银行数据信息的共享
 * Created by mark on 2017/3/8.
 */

public class BankInfoProvider extends ContentProvider {

    private BankOpenHelper mHelper;

    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int ACCOUNT = 1;

    private static final int SINGLE_ACCOUNT = 2;

    static {
        // 训练匹配器
        sUriMatcher.addURI("com.common.mark.job_summary", "account", ACCOUNT);

        // 单条记录
        sUriMatcher.addURI("com.common.mark.job_summary", "account/#", SINGLE_ACCOUNT);
    }

    public BankInfoProvider() {
        super();
    }

    @Override
    public boolean onCreate() {
        mHelper = new BankOpenHelper(getContext());
        return false;
    }

    /**
     * 查询数据库
     *
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        int code = sUriMatcher.match(uri);
        if (code == ACCOUNT) {
            SQLiteDatabase db = mHelper.getReadableDatabase();
            Cursor cursor = db.query("account", projection, null, null, null, null, null);
            return cursor;
        } else {
            throw new IllegalArgumentException("根据法律规定，你无权使用查询权限。");
        }

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int res = sUriMatcher.match(uri);
        if (res == ACCOUNT) {
            // 多条记录
            return "vnd.android.cursor.dir/account";
        } else if (res == SINGLE_ACCOUNT) {
            // 单条记录
            return "vnd.android.cursor.item/account";
        }
        return null;
    }

    /**
     * 数据库中插入一条数据
     *
     * @param uri
     * @param values
     * @return
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int code = sUriMatcher.match(uri);
        if (code == ACCOUNT) {
            SQLiteDatabase db = mHelper.getWritableDatabase();
            long res = db.insert("account", null, values);
            db.close();
            return Uri.parse("content://com.common.mark.job_summary/account" + res);
        } else {
            throw new IllegalArgumentException("根据法律规定，你无权使用查询权限。");
        }

    }

    /**
     * 删除数据库中的信息
     *
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int code = sUriMatcher.match(uri);
        if (code == ACCOUNT) {
            SQLiteDatabase db = mHelper.getWritableDatabase();
            int res = db.delete("account", selection, selectionArgs);
            db.close();
            return res;
        } else {
            throw new IllegalArgumentException("根据法律规定，你无权使用查询权限。");
        }
    }

    /**
     * 更新数据库信息
     *
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int code = sUriMatcher.match(uri);
        if (code == ACCOUNT) {
            SQLiteDatabase db = mHelper.getWritableDatabase();
            int res = db.update("account", values, selection, selectionArgs);
            db.close();
            return res;
        } else {
            throw new IllegalArgumentException("根据法律规定，你无权使用查询权限。");
        }

    }
}
