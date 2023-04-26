package com.nurfadillahdwi.ebookpahlawan.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.nurfadillahdwi.ebookpahlawan.databinding.ActivityAddIdentityBinding
import com.nurfadillahdwi.ebookpahlawan.helper.showToast

class AddIdentityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddIdentityBinding
    private lateinit var namaSiswa: String
    private var x1: Float = 0.0F
    private var x2: Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddIdentityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
        val editor = sharedPref.edit()
        namaSiswa = sharedPref.getString("nama", "default").toString()
        if (namaSiswa != "default" || namaSiswa.isNotEmpty()) {
            binding.edtNama.text = namaSiswa.toEditable()
        }

        binding.btnMasuk.setOnClickListener {
            val namaUser = binding.edtNama.text.toString().trim()

            if (namaUser.isEmpty()) {
                binding.edtNama.error = "Masukkan nama kamu terlebih dahulu"
            } else {
                editor.putString("nama", namaUser)
                editor.apply()

                val i = Intent(this, KeteranganActivity::class.java)
                startActivity(i)
                Animatoo.animateSlideLeft(this)

                Toast.makeText(
                    applicationContext,
                    "Hai, $namaUser\nSelamat datang di aplikasi EBook Pahlawan Indonesia!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onTouchEvent(touchEvent: MotionEvent): Boolean {
        when (touchEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = touchEvent.x
            }
            MotionEvent.ACTION_UP -> {
                x2 = touchEvent.x
                if (x1 < x2) {
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    Animatoo.animateSlideRight(this)
                } else if (x1 > x2) {
                    if (namaSiswa == "default") {
                        showToast(
                            this@AddIdentityActivity,
                            "Silahkan isi nama kamu terlebih dahulu"
                        )
                    } else {
                        val i = Intent(this, KeteranganActivity::class.java)
                        startActivity(i)
                        Animatoo.animateSlideLeft(this)
                    }
                }
            }
        }
        return false
    }

    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
}