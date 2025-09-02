package ru.mby.myapplication.data

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject

object RemoteRepository {
    // Replace with your GitHub raw JSON URL
    const val CATALOG_URL: String = "https://raw.githubusercontent.com/Maybeoff/pressF/refs/heads/main/app.json"

    private val client: OkHttpClient = OkHttpClient()

    fun fetchCatalog(): List<AppItem> {
        val request = Request.Builder().url(CATALOG_URL).build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) return emptyList()
            val body = response.body?.string() ?: return emptyList()
            return parseApps(JSONArray(body))
        }
    }

    private fun parseApps(array: JSONArray): List<AppItem> {
        val result = mutableListOf<AppItem>()
        for (i in 0 until array.length()) {
            val o: JSONObject = array.optJSONObject(i) ?: continue
            result.add(
                AppItem(
                    id = o.optString("id"),
                    name = o.optString("name"),
                    developer = o.optString("developer"),
                    rating = o.optDouble("rating", 0.0),
                    downloads = o.optLong("downloads", 0L),
                    description = o.optString("description"),
                    version = o.optString("version", "0.0.0"),
                    iconUrl = o.optString("iconUrl", null),
                    downloadUrl = o.optString("downloadUrl", null),
                )
            )
        }
        return result
    }
}


