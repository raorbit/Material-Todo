package com.example.admala.toodle;

import android.content.res.Resources;

/**
 * Created by Rahul Admala on 9/25/15.
 */
public class PreferenceKeys {
    final String night_mode_pref_key;

    public PreferenceKeys(Resources resources){
        night_mode_pref_key = resources.getString(R.string.night_mode_pref_key);
    }
}
