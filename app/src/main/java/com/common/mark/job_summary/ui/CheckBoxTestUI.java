package com.common.mark.job_summary.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.common.mark.job_summary.R;

/**
 * Created by mark on 2017/3/3.
 */

public class CheckBoxTestUI extends Activity {

    private CheckBox mCheckBox1;
    private CheckBox mCheckBox2;
    private CheckBox mCheckBox3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_checkboxtest);

        mCheckBox1 = (CheckBox) findViewById(R.id.checkbox1);
        mCheckBox2 = (CheckBox) findViewById(R.id.checkbox2);
        mCheckBox3 = (CheckBox) findViewById(R.id.checkbox3);

        mCheckBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(CheckBoxTestUI.this, "你选中了北京", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CheckBoxTestUI.this, "北京被取了", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mCheckBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(CheckBoxTestUI.this, "你选中了上海", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CheckBoxTestUI.this, "上海被取了", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mCheckBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(CheckBoxTestUI.this, "你选中了仙桃", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CheckBoxTestUI.this, "仙桃被取了", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
