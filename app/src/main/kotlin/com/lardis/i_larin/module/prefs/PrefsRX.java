package com.lardis.i_larin.module.prefs;

import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.Serializable;


public class PrefsRX {

    private static PrefsRX ourInstance = new PrefsRX();

    public static PrefsRX getInstance() {
        return ourInstance;
    }

    private PrefsRX() {
    }

    RxSharedPreferences rxPreferences;

    public void init(Context context) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        rxPreferences = RxSharedPreferences.create(preferences);

    }

    public void setPref(String name, PrefsTypes prefsTypes, Object value) {

        switch (prefsTypes) {

            case STR:
                rxPreferences.getString(name).set((String) value);
                break;
            case BOOL:
                rxPreferences.getBoolean(name).set((Boolean) value);
                ;
                break;

            case LONG:
                rxPreferences.getLong(name).set((Long) value);
                break;

            case INT:
                rxPreferences.getInteger(name).set((Integer) value);

                break;
            case FLOAT:
                rxPreferences.getFloat(name).set((Float) value);
                break;
        }

    }

    public Preference<? extends Serializable> getPref(String name, PrefsTypes prefsTypes, Object defaultValue)

    {

        switch (prefsTypes) {

            case STR:
                return rxPreferences.getString(name, (String) defaultValue);

            case BOOL:
                return rxPreferences.getBoolean(name, (Boolean) defaultValue);

            case LONG:
                return rxPreferences.getLong(name, (Long) defaultValue);

            case INT:
                return rxPreferences.getInteger(name, (Integer) defaultValue);

            case FLOAT:
                return rxPreferences.getFloat(name, (Float) defaultValue);

        }

        return null;
    }
}
