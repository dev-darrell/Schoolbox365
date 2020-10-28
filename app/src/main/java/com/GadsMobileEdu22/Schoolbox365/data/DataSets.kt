package com.GadsMobileEdu22.Schoolbox365.data

import android.util.Patterns
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.GadsMobileEdu22.Schoolbox365.R

class LoginFields : BaseObservable(){

    var emailInput: String? = null
        private set

    var passwordInput: String? = null
        private set

    var emailError = ObservableField<Int>()

    var passwordError = ObservableField<Int>()

    fun setEmailInput(email: String) {
        emailInput = email
        notifyPropertyChanged(BR.valid)
    }



    fun setPasswordInput(password: String) {
        passwordInput = password
        notifyPropertyChanged(BR.valid)
    }




    @Bindable
    fun isValid(): Boolean {
        var valid: Boolean = isEmailValid(false)
        valid = isPasswordValid(false) && valid
        return valid
    }


    @Bindable
    fun isValidEmail(): Boolean {
        if (emailInput != null && emailInput!!.length > 5) {
            if (Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                emailError.set(null)
                return true
            } else
                emailError.set(R.string.wromg_email_format)
            return false
        } else {
            emailError.set(contextR.string.wrong_email)
            return false
        }

    }

    fun isEmailValid(setMessage: Boolean): Boolean {
        if (emailInput != null && emailInput!!.length > 5) {
            if (Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                emailError.set(null)
                return true
            } else {
                if (setMessage) emailError.set(R.string.wromg_email_format)
                return false
            }
        }
        if (setMessage) emailError.set(R.string.wrong_email)
        return false
    }

    fun isPasswordValid(setMessage: Boolean): Boolean {
        if (passwordInput != null && passwordInput!!.length > 5) {
            passwordError.set(null)
            return true
        } else {
            if (setMessage) passwordError.set(R.string.error_too_short_password)
            return false
        }

    }
}