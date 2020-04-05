package com.example.libraryapplicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                SharedPreferences preferences = getSharedPreferences("pref", MODE_PRIVATE);
        if(preferences.getBoolean("first", false)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, tutorial.class);
            startActivity(intent);
            finish();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("first",true);
            editor.apply();
        }

    }
}
