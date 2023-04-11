package com.nurfadillahdwi.ebookpahlawan.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.nurfadillahdwi.ebookpahlawan.R
import com.nurfadillahdwi.ebookpahlawan.databinding.ActivityPracticeBinding
import com.nurfadillahdwi.ebookpahlawan.helper.reduceFileImage
import com.nurfadillahdwi.ebookpahlawan.helper.showToast
import com.nurfadillahdwi.ebookpahlawan.helper.uriToFile
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class PracticeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeBinding
    private lateinit var viewModel: PracticeViewModel
    private var getFile: File? = null
    private val token: String =
        "0110ad0929162b63b7fec2e9b08aca1d0003e99f2db7e4ec8880ea6f9c5a4d6882a7f78f3fc417725ed158eb95929b7c39ec8b5b5a68b0cc38468979f1b5ef2c6aae8e7c78ca53862b9a415a018e5a16646c9285500137649c9a9ce2b11629a92bf205fa6590f0186756c061e08775a26892cda1242a7e9351e0e3594d4b5a2b"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[PracticeViewModel::class.java]
        setContentView(binding.root)

        binding.addPhoto.setOnClickListener {
            startGallery()
        }
        binding.imageButton.setOnClickListener {
            uploadData()
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

    private fun uploadData() {
        if (getFile != null) {
            val inputName = binding.edtHeroName.text.toString()
            val inputLahir = binding.edtHeroBirthDate.text.toString()
            val inputWafat = binding.edtHeroDeceasedDate.text.toString()
            val inputKeterangan = binding.edtHeroKeterangan.text.toString()
            val inputPeran = binding.edtHeroPeran.text.toString()
            val file = reduceFileImage(getFile as File)

            if (inputName.isNotEmpty() && inputLahir.isNotEmpty() && inputWafat.isNotEmpty() && inputKeterangan.isNotEmpty() && inputPeran.isNotEmpty()) {
                val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                    "files",
                    file.name,
                    requestImageFile
                )

                // Create RequestBody for each form-data field
                val namaRequestBody = inputName.toRequestBody("text/plain".toMediaType())
                val tgl_lahirRequestBody = inputLahir.toRequestBody("text/plain".toMediaType())
                val tgl_wafatRequestBody = inputWafat.toRequestBody("text/plain".toMediaType())
                val keteranganRequestBody = inputKeterangan.toRequestBody("text/plain".toMediaType())
                val peranRequestBody = inputPeran.toRequestBody("text/plain".toMediaType())
                val avatarRequestBody = "null".toRequestBody("text/plain".toMediaType())

                val formDataMap = mutableMapOf<String, String>()
                formDataMap["nama"] = "string"
                formDataMap["tgl_lahir"] = "string"
                formDataMap["tgl_wafat"] = "string"
                formDataMap["keterangan"] = "string"
                formDataMap["peran"] = "string"
//                formDataMap["avatar"] = null

                viewModel.addDataPahlawan(token, formDataMap)

//                viewModel.responseAddDataPahlawan.observe(this) {
//                    viewModel.addImagePahlawan(token, imageMultipart, "api::ebook-pahlawan.ebook-pahlawan", it.data?.id.toString(), "avatar")
//                }

            } else {
                if (inputName.isEmpty()) {
                    binding.edtHeroName.error = "Masukkan nama pahlawan!"
                }
                if (inputLahir.isEmpty()) {
                    binding.edtHeroBirthDate.error = "Masukkan tanggal lahir pahlawan!"
                }
                if (inputWafat.isEmpty()) {
                    binding.edtHeroDeceasedDate.error = "Masukkan tanggal meninggal pahlawan!"
                }
                if (inputKeterangan.isEmpty()) {
                    binding.edtHeroKeterangan.error = "Masukkan keterangan pahlawan!"
                }
                if (inputPeran.isEmpty()) {
                    binding.edtHeroPeran.error = "Masukkan peran pahlawan!"
                }
            }
        } else {
            showToast(this@PracticeActivity, "Upload gambar terlebih dahulu")
        }
    }
}