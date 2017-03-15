package com.common.mark.job_summary.ui;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.common.mark.job_summary.R;

/**
 * Created by mark on 2017/3/3.
 */

public class EditTextTestUI extends Activity {

    private EditText mEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_edittexttest);

        mEditText = (EditText) findViewById(R.id.edittext);

        mEditText.setText("这是EditTextTest");
    }
}
