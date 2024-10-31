package com.example.medic.data

import android.content.Context
import com.example.medic.domain.Data
import com.google.gson.Gson
import com.example.medic.R

fun readJsonFromRaw(context: Context): String {
    val inputStream = context.resources.openRawResource(R.raw.data)
    return inputStream.bufferedReader().use { it.readText() }
}

fun parseJson(jsonString: String): Data {
    val gson = Gson()
    return gson.fromJson(jsonString, Data::class.java)
}