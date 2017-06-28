package com.sampleapp.data.cache

import android.content.Context
import android.content.SharedPreferences

import com.google.gson.Gson

import java.lang.reflect.Type

class BaseCache(private val context: Context, private val gson: Gson) {

    private lateinit var prefStore: SharedPreferences

    init {
        initPreference()
    }

    private fun initPreference() {
        prefStore = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    }

    protected fun <T> retrieveObject(key: String, clazz: Type): T {
        return gson.fromJson<T>(prefStore.getString(key, ""), clazz)
    }

    protected fun <T> insert(key: String, `object`: T) {
        val value = gson.toJson(`object`)
        prefStore.edit().putString(key, value).apply()
    }

    protected fun isExist(key: String): Boolean {
        return prefStore.contains(key)
    }
}
