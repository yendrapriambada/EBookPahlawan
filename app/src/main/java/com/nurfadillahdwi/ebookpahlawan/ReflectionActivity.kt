package com.nurfadillahdwi.ebookpahlawan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ReflectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reflection)
        supportActionBar?.hide()
    }
}