package com.nurfadillahdwi.ebookpahlawan.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.nurfadillahdwi.ebookpahlawan.R
import com.nurfadillahdwi.ebookpahlawan.databinding.ActivityMainBinding
import com.nurfadillahdwi.ebookpahlawan.databinding.ActivityPracticeBinding
import com.nurfadillahdwi.ebookpahlawan.helper.uriToFile
import java.io.File

class PracticeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeBinding
    private var getFile: File? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addPhoto.setOnClickListener {
            startGallery()
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Pilih Gambar Pahlawan")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri

            val myFile = uriToFile(selectedImg, this@PracticeActivity)

            getFile = myFile

            binding.addPhoto.setImageURI(selectedImg)
        }
    }
}