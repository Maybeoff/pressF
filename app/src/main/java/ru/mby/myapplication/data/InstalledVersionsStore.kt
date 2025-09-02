package ru.mby.myapplication.data

import android.content.Context
import android.content.SharedPreferences

class InstalledVersionsStore(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("pressf_installed_versions", Context.MODE_PRIVATE)

    fun getInstalledVersion(appId: String): String? = prefs.getString(appId, null)

    fun setInstalledVersion(appId: String, version: String) {
        prefs.edit().putString(appId, version).apply()
    }
}


