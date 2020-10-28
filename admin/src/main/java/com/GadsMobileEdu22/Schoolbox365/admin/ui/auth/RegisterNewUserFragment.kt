package com.GadsMobileEdu22.Schoolbox365.admin.ui.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.GadsMobileEdu22.Schoolbox365.admin.R
import com.GadsMobileEdu22.Schoolbox365.admin.databinding.RegisterNewUserFragmentBinding
import om.GadsMobileEdu22.Schoolbox365.core.data.User

class RegisterNewUserFragment : Fragment() {

    private val viewModel: RegisterNewUserViewModel by viewModels()
    private lateinit var binding: RegisterNewUserFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = RegisterNewUserFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createUserBtn.setOnClickListener {
            if (valid()) {
                val user = User(
                name = binding.firstNameEdtxt.text.toString(),
                emailAddress = binding.emailEdtxt.text.toString(),
                userType = if (binding.lecturerRadioBtn.isSelected )"lecturer" else "student",
                userSex = if (binding.maleRadioBtn.isSelected) "male" else "female",
                userDepartment = binding.userDeptEdtxt.text.toString(),
                userFaculty = binding.userFacultyEdtxt.text.toString(),
                userProgramOfStudy = binding.userCoursesEdtxt.text.toString())

                viewModel.signUp(user, binding.)
            }
        }
    }

}