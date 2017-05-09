package com.android.newssample.data.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class BaseCache {

    private SharedPreferences prefStore;
    private Context context;
    private Gson gson;

    public BaseCache(Context context, Gson gson) {
        this.context = context;
        this.gson = gson;
        initPreference();
    }

    private void initPreference() {
        prefStore = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
    }

    protected <T> T retrieveObject(String key, Type clazz) {
        return gson.fromJson(prefStore.getString(key, ""), clazz);
    }

    protected <T> void insert(String key, T object) {
        String value = gson.toJson(object);
        prefStore.edit().putString(key, value).apply();
    }

    protected boolean isExist(String key) {
        return prefStore.contains(key);
    }
}
