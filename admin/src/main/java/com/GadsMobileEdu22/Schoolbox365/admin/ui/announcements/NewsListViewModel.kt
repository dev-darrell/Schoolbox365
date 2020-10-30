package com.GadsMobileEdu22.Schoolbox365.admin.ui.announcements

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import om.GadsMobileEdu22.Schoolbox365.core.data.News
import java.util.*

class NewsListViewModel : ViewModel() {
    private val ref = FirebaseDatabase.getInstance().getReference("development/news")
    private val _newsList = MutableLiveData<List<News>>()
    private val _message = MutableLiveData<String>()
    val newsList :LiveData<List<News>> = _newsList
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
        val id = ref.push().key
        news.id = id!!

        ref.child(news.id).setValue(news).addOnCompleteListener {uploadTask ->
            if (uploadTask.isSuccessful){
                _message.value = "News Posted"
            }else{
                _message.value = "Something went wrong: ${uploadTask.exception?.localizedMessage}"
            }
        }
    }
}