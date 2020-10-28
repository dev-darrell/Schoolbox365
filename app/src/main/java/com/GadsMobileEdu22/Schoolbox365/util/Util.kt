package com.GadsMobileEdu22.Schoolbox365.util

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

sealed class AuthenticationProgress {
    object Loading : AuthenticationProgress()
    object Done : AuthenticationProgress()
    data class Error(var message: String?) : AuthenticationProgress()
}


@BindingAdapter("error")
fun setError(editText: TextInputLayout, strOrResId: Any?) {
    if (strOrResId is Int) {
        editText.error = editText.context.getString((strOrResId as Int?)!!)
    }
    else {
        editText.error = strOrResId as String?
    }
}

@BindingAdapter("onFocus")
fun bindFocusChange(
        editText: TextInputEditText,
        onFocusChangeListener: View.OnFocusChangeListener?
) {
    if (editText.onFocusChangeListener == null) {
        editText.onFocusChangeListener = onFocusChangeListener
    }
}