package com.nurfadillahdwi.ebookpahlawan.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.nurfadillahdwi.ebookpahlawan.api.ApiConfig
import com.nurfadillahdwi.ebookpahlawan.helper.setupToken
import com.nurfadillahdwi.ebookpahlawan.response.FileResponse
import com.nurfadillahdwi.ebookpahlawan.response.PahlawanData
import com.nurfadillahdwi.ebookpahlawan.response.UploadDataResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PracticeViewModel : ViewModel() {

    private val _responseAddDataPahlawan = MutableLiveData<UploadDataResponse>()
    val responseAddDataPahlawan: LiveData<UploadDataResponse> = _responseAddDataPahlawan

    private val _responseAddImagePahlawan = MutableLiveData<Boolean>()
    val responseAddImagePahlawan: LiveData<Boolean> = _responseAddImagePahlawan

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _onFailure = MutableLiveData<String>()
    val onFailure: LiveData<String> = _onFailure

    fun addDataPahlawan(token: String, pahlawanData: PahlawanData) {
        _isLoading.value = true

        // Convert PahlawanData object to JSON string
        val jsonData = Gson().toJson(pahlawanData)

        // Create RequestBody for JSON data
        val jsonDataRequestBody = jsonData.toRequestBody("application/json".toMediaType())

        // Create map of form-data parameters
        val formDataMap = mutableMapOf<String, RequestBody>()
        formDataMap["data"] = jsonDataRequestBody


        val client = ApiConfig.getApiService().uploadDataPahlawan(setupToken(token), formDataMap)
        client.enqueue(object : Callback<UploadDataResponse> {
            override fun onResponse(
                call: Call<UploadDataResponse>,
                response: Response<UploadDataResponse>
            ) {
                _isLoading.postValue(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _responseAddDataPahlawan.postValue(response.body())
                        Log.e(TAG, "SUKSES")
                    } else {
                        _onFailure.postValue("Is Not Success: ${response.message()}")
                        Log.e(TAG, "isNotSuccess: ${response.message()}")
                    }
                } else {
                    Log.e(TAG, "isNotSuccess: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UploadDataResponse>, t: Throwable) {
                _isLoading.postValue(false)
                _onFailure.postValue("On Failure: ${t.message.toString()}")
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun addImagePahlawan(
        token: String,
        image: MultipartBody.Part,
        ref: String,
        refId: String,
        field: String
    ) {
        _isLoading.value = true
        val refData = ref.toRequestBody("text/plain".toMediaType())
        val refIdData = refId.toRequestBody("text/plain".toMediaType())
        val fieldData = field.toRequestBody("text/plain".toMediaType())

        val client = ApiConfig.getApiService()
            .uploadImagePahlawan(setupToken(token), image, refData, refIdData, fieldData)
        client.enqueue(object : Callback<FileResponse> {
            override fun onResponse(
                call: Call<FileResponse>,
                response: Response<FileResponse>
            ) {
                _isLoading.postValue(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()?.fileResponse!![0]
                    if (responseBody != null) {
                        _responseAddImagePahlawan.postValue(true)
                        Log.e(TAG, "SUKSES")
                    } else {
                        _responseAddImagePahlawan.postValue(false)
                        _onFailure.postValue("Is Not Success: ${response.message()}")
                        Log.e(TAG, "isNotSuccess: ${response.message()}")
                    }
                } else {
                    _responseAddImagePahlawan.postValue(false)
                    Log.e(TAG, "isNotSuccess: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<FileResponse>, t: Throwable) {
                _responseAddImagePahlawan.postValue(false)
                _isLoading.postValue(false)
                _onFailure.postValue("On Failure: ${t.message.toString()}")
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object {
        private const val TAG = "PracticeViewModel"
    }
}