package com.GadsMobileEdu22.Schoolbox365.admin.ui.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.GadsMobileEdu22.Schoolbox365.admin.R

class RegisterNewUserFragment : Fragment() {

    private lateinit var viewModel: RegisterNewUserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.register_new_user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterNewUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}