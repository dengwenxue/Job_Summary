package com.common.mark.job_summary.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.common.mark.job_summary.db.BankOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对银行数据库data 的操作
 * Created by mark on 2017/3/7.
 */

public class BankDBDao {

    private BankOpenHelper helper;

    public BankDBDao(Context context) {
        helper = new BankOpenHelper(context);
    }

    /**
     * 添加一条账户信息
     *
     * @param name  姓名
     * @param money 钱
     * @return res 添加一条信息
     */
    public long add(String name, float money) {
        if (isUserExit(name)) {
            System.out.println("用户已经存在");
            return -1;
        }

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("money", money);
        long res = db.insert("account", null, values);

        db.close();
        return res;
    }

    /**
     * 是否删除数据库中的信息
     *
     * @param name 姓名
     * @return 是否删除
     */
    public boolean delete(String name) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int res = db.delete("account", "name=?", new String[]{name});
        db.close();

        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否修改储户信息
     *
     * @param name  姓名
     * @param money 钱
     * @return 是否修改成功
     */
    public boolean update(String name, float money) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("money", money);
        int res = db.update("account", values, "name=?", new String[]{"name"});
        db.close();
        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查询某个用户多少钱
     *
     * @param name
     * @return 钱
     */
    public float getUserMoney(String name) {
        float money = 0;

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("account", new String[]{"money"}, "name=?", new String[]{name}, null, null, null);
        if (cursor.moveToNext()) {
            money = cursor.getFloat(0);
        }
        cursor.close();
        db.close();
        return money;
    }

    /**
     * 判断用户是否存在
     *
     * @param name
     * @return
     */
    public boolean isUserExit(String name) {
        boolean res = false;

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("account", null, "name=?", new String[]{name}, null, null, null);
        if (cursor.moveToNext()) {
            res = true;
        }
        cursor.close();
        db.close();
        return res;
    }

    /**
     * 查询所有的储户信息
     *
     * @return
     */
    public List<Map<String, Object>> AllUsers() {
        List<Map<String, Object>> allUsers = new ArrayList<Map<String, Object>>();

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("account", new String[]{"_id", "name", "money"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Map<String, Object> user = new HashMap<String, Object>();
            user.put("_id", cursor.getInt(0));
            user.put("name", cursor.getString(0));
            user.put("money", cursor.getFloat(0));
            allUsers.add(user);
        }
        cursor.close();
        db.close();
        return allUsers;
    }

}
