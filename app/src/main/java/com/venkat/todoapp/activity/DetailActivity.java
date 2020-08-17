package com.venkat.todoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.venkat.todoapp.constant.AppConstants;
import com.venkat.todoapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView t1;
    private TextView t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        t1 = findViewById(R.id.textView);
        t2 = findViewById(R.id.textView2);

        Intent intent = getIntent();
        t1.setText(intent.getStringExtra(AppConstants.TITLE));
        t2.setText(intent.getStringExtra(AppConstants.DESCRIPTION));
    }
}
