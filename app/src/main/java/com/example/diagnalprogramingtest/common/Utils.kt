package com.example.diagnalprogramingtest.common

import android.content.Context

fun Context.readJsonFromAssets(fileName: String): String {
    return assets.open(fileName).bufferedReader().use { it.readText() }
}