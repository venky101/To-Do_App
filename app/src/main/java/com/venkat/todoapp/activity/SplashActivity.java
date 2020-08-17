package com.venkat.todoapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.venkat.todoapp.constant.PreferenceConstants;
import com.venkat.todoapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setSharedPreference();
        checkLoginStatus();
    }

    private void setSharedPreference() {
        sharedPreferences = getSharedPreferences(PreferenceConstants.NAME, MODE_PRIVATE);
    }

    private void checkLoginStatus() {
        Boolean isLoggenIn = sharedPreferences.getBoolean(PreferenceConstants.IS_LOGGED_IN, false);
        if(isLoggenIn) {
            Intent intent = new Intent(this, MyNotesActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
