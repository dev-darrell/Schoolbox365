package com.GadsMobileEdu22.Schoolbox365.admin.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import om.GadsMobileEdu22.Schoolbox365.core.data.AuthenticationProgress
import om.GadsMobileEdu22.Schoolbox365.core.data.AuthenticationProgress.*
import om.GadsMobileEdu22.Schoolbox365.core.data.User


class RegisterNewUserViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val ref = FirebaseDatabase.getInstance().getReference("development/user")
    private val _currentUser = MutableLiveData<User>()
    private val _user = MutableLiveData<FirebaseUser>()
    val user: LiveData<FirebaseUser> = _user
    private val _progress = MutableLiveData<AuthenticationProgress>()
    val progress: LiveData<AuthenticationProgress> = _progress

    fun signUp(user: User, password:String){
//        signOut()
        _progress.value = Loading
        auth.createUserWithEmailAndPassword(user.emailAddress,password ). addOnCompleteListener {authTask ->
            if (authTask.isSuccessful){
               user.userId =  auth.currentUser?.uid!!

                ref.child(user.userId).setValue(user).addOnCompleteListener {uploadTask ->
                    if (uploadTask.isSuccessful){
                        _progress.value = Done
                    }else{
                        _progress.value = Error(uploadTask.exception?.localizedMessage)
                    }

                }

            }else{
                _progress.value = Error(authTask.exception?.localizedMessage)
            }
        }
    }

    private fun signOut() {
        auth.signOut()
    }
}