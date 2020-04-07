package com.example.libraryapplicationproject;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;


public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences preferences1 = getSharedPreferences("settings", MODE_PRIVATE);

        if(preferences.getBoolean("first", false)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("first",true);
            editor.apply();
            Intent intent = new Intent(this, tutorial.class);
            startActivity(intent);
            finish();
        }
        if(preferences1.getBoolean("pictures", false)){
            SettingsActivity.pictureSetting = true;
        }else{
            SettingsActivity.pictureSetting = false;
        }

        if(preferences1.getBoolean("refresh", false)){
            SettingsActivity.refreshSetting = true;
        }else{
            SettingsActivity.refreshSetting = false;
        }
    }
}
