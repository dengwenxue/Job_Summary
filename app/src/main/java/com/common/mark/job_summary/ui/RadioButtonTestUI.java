package com.common.mark.job_summary.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.common.mark.job_summary.R;

/**
 * Created by mark on 2017/3/3.
 */

public class RadioButtonTestUI extends Activity {

    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_radiobuttontest);

        mRadioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        mRadioButton1 = (RadioButton) findViewById(R.id.radiobtn1);
        mRadioButton2 = (RadioButton) findViewById(R.id.radiobtn2);
        mRadioButton3 = (RadioButton) findViewById(R.id.radiobtn3);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == mRadioButton1.getId()) {
                    Toast.makeText(RadioButtonTestUI.this, "北京", Toast.LENGTH_SHORT).show();
                } else if (checkedId == mRadioButton2.getId()) {
                    Toast.makeText(RadioButtonTestUI.this, "上海", Toast.LENGTH_SHORT).show();
                } else if (checkedId == mRadioButton3.getId()) {
                    Toast.makeText(RadioButtonTestUI.this, "仙桃", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
