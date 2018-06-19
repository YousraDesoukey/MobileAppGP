package com.asu.hebagp;

import android.content.SharedPreferences;
import android.support.multidex.MultiDexApplication;

/**
 * Created by Mostafa on 2018/01/09.
 */

public class StartApplication extends MultiDexApplication {

    public static final int en_lang = 0;
    public static final int ar_lang = 1;
    public static final int de_lang = 2;
    public static final int es_lang = 3;
    public static final int fr_lang = 4;
    public static final int ru_lang = 5;

    public static int language = en_lang ; // 0 en , 1 ar , 2 de , 3 es , 4 fr , 5 ru

    public static SharedPreferences sp;
    public static String spName = "LanguuageSharedPref";
    public static String languageTag = "LanguageTag";

    @Override
    public void onCreate() {
        super.onCreate();

        setSP();
    }

    private void setSP() {
        sp = getSharedPreferences(spName,0);
        language = sp.getInt(languageTag,en_lang);
    }
}
