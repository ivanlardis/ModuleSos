package com.lardis.i_larin.module.prefs;

import com.f2prateek.rx.preferences.Preference;

import java.io.Serializable;

import rx.Observable;
import rx.functions.Func1;


public enum Prefs {
    USER_ID(PrefsTypes.INT, -1 ),
    FIRST_NAME(PrefsTypes.STR, ""),
    LAST_NAME(PrefsTypes.STR, ""),
    PHOTO_URL(PrefsTypes.STR, ""),

    LOGIN(PrefsTypes.STR, "admin"),

    PASWORD(PrefsTypes.STR, "1234");


    PrefsTypes prefsTypes;

    public PrefsTypes getPrefsTypes() {
        return prefsTypes;
    }

    public Object getValue() {
        return value;
    }

    Object value;

    Prefs(PrefsTypes prefsTypes, Object value) {
        this.prefsTypes = prefsTypes;
        this.value = value;
    }

    Preference<? extends Serializable> get() {
        return PrefsRX.getInstance().getPref(name(), prefsTypes, value);

    }

    public void set(Object o) {
        PrefsRX.getInstance().setPref(name(), prefsTypes, o);

    }

    public String getString()

    {
        return (String) get().get();
    }

    public Float getFloat()

    {
        return (Float) get().get();
    }

    public Integer getInteger()

    {
        return (Integer) get().get();
    }

    public Boolean getBoolean()

    {
        return (Boolean) get().get();
    }

    public Long getLong()

    {
        return (Long) get().get();
    }

    public Observable<String> getAsObservableString()

    {
        return get().asObservable().map(new Func1<Serializable, String>() {
            @Override
            public String call(final Serializable serializable) {
                return (String) serializable;
            }
        });


    }

    public Observable<Boolean> getAsObservableBoolean()

    {
        return get().asObservable().map(new Func1<Serializable, Boolean>() {
            @Override
            public Boolean call(final Serializable serializable) {
                return (Boolean) serializable;
            }
        });


    }

    public Observable<Long> getAsObservableLong()

    {
        return get().asObservable().map(new Func1<Serializable, Long>() {
            @Override
            public Long call(final Serializable serializable) {
                return (Long) serializable;
            }
        });

    }

    public Observable<Integer> getAsObservableInteger()

    {
        return get().asObservable().map(new Func1<Serializable, Integer>() {
            @Override
            public Integer call(final Serializable serializable) {
                return (Integer) serializable;
            }
        });


    }

    public Observable<Float> getAsObservableFloat()

    {
        return get().asObservable().map(new Func1<Serializable, Float>() {
            @Override
            public Float call(final Serializable serializable) {
                return (Float) serializable;
            }
        });


    }

}

