package com.nurfadillahdwi.ebookpahlawan.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.nurfadillahdwi.ebookpahlawan.databinding.ActivityPracticeBinding
import com.nurfadillahdwi.ebookpahlawan.helper.reduceFileImage
import com.nurfadillahdwi.ebookpahlawan.helper.showToast
import com.nurfadillahdwi.ebookpahlawan.helper.uriToFile
import com.nurfadillahdwi.ebookpahlawan.response.PahlawanData
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PracticeActivity : AppCompatActivity() {
    private var x1: Float = 0.0F
    private var x2: Float = 0.0F
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

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
        viewModel.onFailure.observe(this) {
            if (it.contains("Expected BEGIN_OBJECT but was BEGIN_ARRAY")){
                showToast(this, "Berhasil!")
            }
            else{
                AlertDialog.Builder(this).apply {
                    setTitle("Oops!")
                    setMessage(it)
                    setPositiveButton("Try Again") { dialog, _ ->
                        dialog.cancel()
                    }
                    create()
                    show()
                }
            }
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

                // Create PahlawanData object
                val pahlawanData = PahlawanData(
                    nama = inputName,
                    tgl_lahir = inputLahir,
                    tgl_wafat = inputWafat,
                    keterangan = inputKeterangan,
                    peran = inputPeran,
                    avatar = null
                )

                viewModel.addDataPahlawan(token, pahlawanData)

                viewModel.responseAddDataPahlawan.observe(this) { addData ->
                    viewModel.addImagePahlawan(token, imageMultipart, "api::ebook-pahlawan.ebook-pahlawan", addData.data?.id.toString(), "avatar")

                    viewModel.responseAddImagePahlawan.observe(this) { addImage ->
                        if (addImage){
                            showToast(this, "Berhasil!")
                        }
                    }
                }

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

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onTouchEvent(touchEvent: MotionEvent): Boolean {
        when (touchEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = touchEvent.x
            }
            MotionEvent.ACTION_UP -> {
                x2 = touchEvent.x
                if (x1 < x2) {
                    val i = Intent(this, MKCActivity::class.java)
                    startActivity(i)
                    Animatoo.animateSlideRight( this)
                } else if (x1 > x2) {
                    val i = Intent(this, ReflectionActivity::class.java)
                    startActivity(i)
                    Animatoo.animateSlideLeft( this)

                }
            }
        }
        return false
    }
}