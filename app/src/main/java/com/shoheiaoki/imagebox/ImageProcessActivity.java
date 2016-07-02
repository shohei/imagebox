package com.shoheiaoki.imagebox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by shohei on 7/2/16.
 */
public class ImageProcessActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_process);

        String s = getIntent().getStringExtra(MainActivity.MAIN_TO_IMAGE_PROCESS);
        setActionBar(s);
    }

    protected void setActionBar(String title){
        setTitle(title);
    }

}
