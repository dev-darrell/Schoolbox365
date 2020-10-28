package com.GadsMobileEdu22.Schoolbox365

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.GadsMobileEdu22.Schoolbox365.data.User
import com.GadsMobileEdu22.Schoolbox365.util.AuthenticationProgress
import com.GadsMobileEdu22.Schoolbox365.util.AuthenticationProgress.*
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import timber.log.Timber

class MainActivityViewModel : ViewModel() {
    private val ref = FirebaseDatabase.getInstance().getReference("development/user")
    private val _currentUser = MutableLiveData<User>()
//    val currentUser: LiveData<User> = _currentUser
    private val _user = MutableLiveData<FirebaseUser>()
    private val auth = FirebaseAuth.getInstance()
    val user: LiveData<FirebaseUser> = _user
    private val _progress = MutableLiveData<AuthenticationProgress>()
    val progress:LiveData<AuthenticationProgress> = _progress

     fun signIn(email: String, password: String) {
        _progress.value = Loading
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                _user.value = auth.currentUser
                _progress.value = Done
            }else{
                _progress.value = Error(it.exception?.localizedMessage)
            }
        }
    }

    fun getCurrentUser() : LiveData<User>{
        val usersList = arrayListOf<User>()
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(data in snapshot.children){
                    val user = data.getValue(User::class.java)
                    user?.let {
                        usersList.add(user)
                    }

                    val authUserId = FirebaseAuth.getInstance().currentUser?.uid

                    _currentUser.value = usersList.single { v: User -> v.userId == authUserId }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                _currentUser.value = null
               Timber.d("Database error %s", error.message)
            }
        })

        return _currentUser
    }


}