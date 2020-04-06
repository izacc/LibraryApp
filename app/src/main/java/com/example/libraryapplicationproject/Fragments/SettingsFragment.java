package com.example.libraryapplicationproject.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.libraryapplicationproject.R;
import com.example.libraryapplicationproject.SettingsActivity;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

public class SettingsFragment extends PreferenceFragmentCompat {

    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        Preference settings1 = findPreference("feedback");
        final SwitchPreference settings2 = findPreference("pictures");
        final SwitchPreference settings3 = findPreference("refresh");



        SwitchPreference.OnPreferenceChangeListener changeListener = new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                //Put this method in splash activity to work properly
                if (settings2.isChecked()){
                    SettingsActivity.pictureSetting = false;
                }else if(!settings2.isChecked()){
                    SettingsActivity.pictureSetting = true;
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
                }else if(!settings3.isChecked()){
                    SettingsActivity.refreshSetting = true;
                }

                //need to find a way to refresh the adapters

                return true;
            }
        };

        Preference.OnPreferenceClickListener clickListener = new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, "izacc.casey-lucas01@stclairconnect.ca");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback to the Yozacc Company");
                intent.putExtra(Intent.EXTRA_TEXT, "I think this app is...");
                if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivity(intent);
                }
                return false;

            }
        };






        settings3.setOnPreferenceChangeListener(changeListener2);
        settings2.setOnPreferenceChangeListener(changeListener);
        settings1.setOnPreferenceClickListener(clickListener);
    }


}

