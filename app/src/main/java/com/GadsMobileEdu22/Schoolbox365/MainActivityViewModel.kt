package com.GadsMobileEdu22.Schoolbox365

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.GadsMobileEdu22.Schoolbox365.data.LoginFields
import com.GadsMobileEdu22.Schoolbox365.util.AuthenticationProgress
import com.GadsMobileEdu22.Schoolbox365.util.AuthenticationProgress.*
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivityViewModel : ViewModel() {
    private val _user = MutableLiveData<FirebaseUser>()
    private val auth = FirebaseAuth.getInstance()
    val user: LiveData<FirebaseUser> = _user
    val login = LoginFields()
    private val _progress = MutableLiveData<AuthenticationProgress>()
    val progress:LiveData<AuthenticationProgress> = _progress

    val onFocusEmail: View.OnFocusChangeListener
    val onFocusPassword: View.OnFocusChangeListener

    init {
        onFocusEmail = View.OnFocusChangeListener { v, hasFocus ->
            val et: TextInputEditText = v as TextInputEditText
            if (et.text!!.isNotEmpty() && !hasFocus) {
                login.isEmailValid(true)
            }
        }
        onFocusPassword = View.OnFocusChangeListener { v, hasFocus ->
            val et: TextInputEditText = v as TextInputEditText
            if (et.text!!.isNotEmpty() && !hasFocus) {
                login.isPasswordValid(true)
            }
        }
    }

    private fun signIn(email: String, password: String) {
        _progress.value = Loading
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                _user.value = auth.currentUser
                _progress.value = Done
            }else{
                _progress.value = Error(it.exception?.localizedMessage)
            }
        }
    }fun onButtonClick() {
        if (login.isValid()) {

            signIn(login.emailInput!!, login.passwordInput!!)
        }
    }
}