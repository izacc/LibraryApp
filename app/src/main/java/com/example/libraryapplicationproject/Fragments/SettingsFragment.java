package com.example.libraryapplicationproject.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.libraryapplicationproject.MainActivity;
import com.example.libraryapplicationproject.R;
import com.example.libraryapplicationproject.SettingsActivity;
import com.example.libraryapplicationproject.creditsActivity;

import androidx.navigation.Navigation;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends PreferenceFragmentCompat {
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);


        Preference settings1 = findPreference("feedback");
        Preference settings4 = findPreference("phone");
        Preference setting5 = findPreference("credits");
        final SharedPreferences settingPreferences = getActivity().getSharedPreferences("settings", MODE_PRIVATE);
        final SharedPreferences.Editor editor = settingPreferences.edit();
        final SwitchPreference settings2 = findPreference("pictures");
        final SwitchPreference settings3 = findPreference("refresh");

        SwitchPreference.OnPreferenceChangeListener changeListener = new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                //Put this method in splash activity to work properly
                if (settings2.isChecked()){
                    SettingsActivity.pictureSetting = false;
                    editor.putBoolean("pictures", false);
                    editor.apply();
                }else if(!settings2.isChecked()){
                    SettingsActivity.pictureSetting = true;
                    editor.putBoolean("pictures", true);
                    editor.apply();
                }

                //need to find a way to refresh the adapters

                return true;
            }
        };

        SwitchPreference.OnPreferenceChangeListener changeListener2 = new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                //Put this method in splash activity to work properly
                if (settings3.isChecked()){
                    SettingsActivity.refreshSetting = false;
                    editor.putBoolean("refresh", false);
                    editor.apply();
                }else if(!settings3.isChecked()){
                    SettingsActivity.refreshSetting = true;
                    editor.putBoolean("refresh", true);
                    editor.apply();

                }

                //need to find a way to refresh the adapters

                return true;
            }
        };

        Preference.OnPreferenceClickListener clickListener = new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto: izacc.casey-lucas01@stclairconnect.ca"));
                intent.putExtra(Intent.EXTRA_CC, "YonisEmail");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback to the Yozacc Company");
                intent.putExtra(Intent.EXTRA_TEXT, "I think this app is...");
                if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivity(intent);
                }
                return false;

            }
        };


        Preference.OnPreferenceClickListener clickListener2 = new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto: 2262603329"));

                intent.putExtra("sms_body", "Hello, Izacc and Yonis..");
                if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivity(intent);
                }
                return false;

            }
        };

        //PUT CODE IN HERE TO LAUNCH ACTIVITY ETC
        Preference.OnPreferenceClickListener clickListener3 = new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                Intent intent = new Intent(getActivity(), creditsActivity.class);
                startActivity(intent);
                return false;

            }
        };




        setting5.setOnPreferenceClickListener(clickListener3);
        settings4.setOnPreferenceClickListener(clickListener2);
        settings3.setOnPreferenceChangeListener(changeListener2);
        settings2.setOnPreferenceChangeListener(changeListener);
        settings1.setOnPreferenceClickListener(clickListener);
    }


}

