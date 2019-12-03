package com.company.immersionstatusbar;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CreateTwoProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_two_progress);
        initEvent();
    }

    private void initEvent() {
        Intent intent = new Intent(CreateTwoProgressActivity.this, MyService.class);
        startService(intent);
    }
}
