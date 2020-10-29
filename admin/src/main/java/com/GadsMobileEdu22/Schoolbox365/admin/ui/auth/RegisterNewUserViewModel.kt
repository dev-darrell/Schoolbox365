package com.GadsMobileEdu22.Schoolbox365.admin.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import om.GadsMobileEdu22.Schoolbox365.core.data.User


class RegisterNewUserViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val ref = FirebaseDatabase.getInstance().getReference("development/user")
    private val _currentUser = MutableLiveData<User>()
    private val _user = MutableLiveData<FirebaseUser>()
    val user: LiveData<FirebaseUser> = _user
    private val _progress = MutableLiveData<AuthProgress>()
    val progress: LiveData<AuthProgress> = _progress

    fun signUp(user: User, password:String){
//        signOut()
        _progress.value = AuthProgress.Loading
        auth.createUserWithEmailAndPassword(user.emailAddress,password ). addOnCompleteListener {authTask ->
            if (authTask.isSuccessful){
               user.userId =  auth.currentUser?.uid!!

                ref.child(user.userId).setValue(user).addOnCompleteListener {uploadTask ->
                    if (uploadTask.isSuccessful){
                        _progress.value = AuthProgress.Done
                    }else{
                        _progress.value = AuthProgress.Error(uploadTask.exception?.localizedMessage)
                    }

                }

            }else{
                _progress.value = AuthProgress.Error(authTask.exception?.localizedMessage)
            }
        }
    }

    private fun signOut() {
        auth.signOut()
    }
}


sealed class AuthProgress {
    object Loading : AuthProgress()
    object Done : AuthProgress()
    data class Error(var message: String?) : AuthProgress()
}