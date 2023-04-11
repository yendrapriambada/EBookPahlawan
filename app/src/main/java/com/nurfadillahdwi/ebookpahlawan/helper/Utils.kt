package com.nurfadillahdwi.ebookpahlawan.helper


import android.app.Activity
import android.widget.Toast


fun setupToken(token: String): String {
    return "Bearer $token"
}

fun showToast(context: Activity, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


