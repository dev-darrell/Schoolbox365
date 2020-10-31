package com.GadsMobileEdu22.Schoolbox365.admin.ui.announcements

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat.JPEG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import om.GadsMobileEdu22.Schoolbox365.core.data.News
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.util.*
import java.util.concurrent.CountDownLatch

class NewsListViewModel : ViewModel() {
    private val ref = FirebaseDatabase.getInstance().getReference("development/news")
    private val _newsList = MutableLiveData<List<News>>()
    private val _message = MutableLiveData<String>()
    private val _isUploadComplete = MutableLiveData<Boolean>()
    val isUploadComplete = _isUploadComplete
    val newsList :LiveData<List<News>> = _newsList
    private lateinit var imageBitmap : Bitmap
    val message :LiveData<String> = _message

    init {
        val list = arrayListOf<News>()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children) {
                    val news = data.getValue(News::class.java)
                    news?.let{ list.add(news) }
                }

                _newsList.value = list

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }

    fun uploadNews(news: News){

        fun finishUpload(imageUrl: String) {
            val id = ref.push().key
            news.id = id!!
            news.image = imageUrl
            ref.child(news.id).setValue(news).addOnCompleteListener {uploadTask ->
                if (uploadTask.isSuccessful){
                    _message.value = "News Posted"
                }else{
                    _message.value = "Something went wrong: ${uploadTask.exception?.localizedMessage}"
                }
            }
        }

        fun uploadImageToFirebase(bitmap: Bitmap) {
            _isUploadComplete.value = false
            var imageUrl = ""
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(JPEG, 40, outputStream)
            val data = outputStream.toByteArray()
            val ref = FirebaseStorage.getInstance().getReference("images/news")
            ref.putBytes(data).addOnCompleteListener {
                if (it.isSuccessful) {
                    it.result?.storage?.downloadUrl?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            imageUrl = task.result.toString()
                            finishUpload(imageUrl)
                        }
                        else {
                            Timber.d("ImageUpload error : %s", task.exception?.localizedMessage)
                        }
                    }
                }
                else {
                    _isUploadComplete.value = false
                }
            }
        }

        uploadImageToFirebase(imageBitmap)

    }


    fun setImageBitmap(bitmap: Bitmap){
        imageBitmap = bitmap
    }
}