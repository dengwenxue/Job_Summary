package com.common.mark.job_summary.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.common.mark.job_summary.R;

/**
 * Created by mark on 2017/3/3.
 */

public class ImageButtonTestUI extends Activity implements View.OnClickListener {

    private ImageButton mImageButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_imagebuttontest);

        mImageButton = (ImageButton) findViewById(R.id.imgbtn_imagebutton);
        mImageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imgbtn_imagebutton){
            Toast.makeText(this,"你点击了确认键",Toast.LENGTH_LONG).show();
        }
    }
}
