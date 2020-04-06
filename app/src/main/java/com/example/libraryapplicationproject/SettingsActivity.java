package com.example.libraryapplicationproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.libraryapplicationproject.Fragments.SettingsFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;

public class SettingsActivity extends AppCompatActivity {
    public static boolean pictureSetting = false;
    public static boolean refreshSetting = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();




    }



}