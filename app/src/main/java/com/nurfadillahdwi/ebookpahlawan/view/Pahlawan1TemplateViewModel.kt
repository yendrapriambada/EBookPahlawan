package com.nurfadillahdwi.ebookpahlawan.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nurfadillahdwi.ebookpahlawan.api.ApiConfig
import com.nurfadillahdwi.ebookpahlawan.helper.setupToken
import com.nurfadillahdwi.ebookpahlawan.response.PahlawansResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Pahlawan1TemplateViewModel : ViewModel() {

    private val _responsePahlawan = MutableLiveData<PahlawansResponse>()
    val responsePahlawan: LiveData<PahlawansResponse> = _responsePahlawan

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getPahlawans(token: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getPahlawan(setupToken(token))
        client.enqueue(object : Callback<PahlawansResponse> {
            override fun onResponse(
                call: Call<PahlawansResponse>,
                response: Response<PahlawansResponse>
            ) {
                _isLoading.postValue(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _responsePahlawan.postValue(response.body())
                        Log.e(TAG, "SUKSES")
                    } else {
                        Log.e(TAG, "isNotSuccess: ${response.message()}")
                    }
                } else {
                    Log.e(TAG, "isNotSuccess: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PahlawansResponse>, t: Throwable) {
                _isLoading.postValue(false)
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object {
        private const val TAG = "Pahlawan1TemplateViewModel"
    }
}