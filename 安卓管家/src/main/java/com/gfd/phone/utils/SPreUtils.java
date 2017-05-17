package com.gfd.phone.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.gfd.phone.app.App;


public class SPreUtils {

    private static final String SP_NAME = "congif";

    /**
     * 保存布尔类型的值
     * @param key
     * @param value
     */
    public static void saveBoolean(String key, boolean value) {
        SharedPreferences sp = App.appContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * 获取布尔类型的值
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(String key,boolean defaultValue){
        SharedPreferences sp = App.appContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key,defaultValue);

    }
}
