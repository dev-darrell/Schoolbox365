package com.GadsMobileEdu22.Schoolbox365.util



sealed class AuthenticationProgress {
    object Loading : AuthenticationProgress()
    object Done : AuthenticationProgress()
    data class Error(var message: String?) : AuthenticationProgress()
}