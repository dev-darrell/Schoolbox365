package com.GadsMobileEdu22.Schoolbox365.admin.ui.auth

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.GadsMobileEdu22.Schoolbox365.admin.databinding.RegisterNewUserFragmentBinding
import com.google.android.material.snackbar.Snackbar
import om.GadsMobileEdu22.Schoolbox365.core.data.User

class  RegisterNewUserFragment : Fragment() {

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
                name = binding.firstNameEdtxt.text.toString() + " " + binding.lastNameEdtxt.text.toString(),
                emailAddress = binding.emailEdtxt.text.toString(),
                userType = if (binding.lecturerRadioBtn.isSelected )"lecturer" else "student",
                userSex = if (binding.maleRadioBtn.isSelected) "male" else "female",
                userDepartment = binding.userDeptEdtxt.text.toString(),
                userFaculty = binding.userFacultyEdtxt.text.toString(),
                userProgramOfStudy = binding.userCoursesEdtxt.text.toString())

                viewModel.signUp(user, binding.passwordEdtxt.text.toString())
            }else{
                Snackbar.make(requireView().rootView, "Please Fill all Fields correctly",Snackbar.LENGTH_LONG ).show()
            }
        }

        viewModel.progress.observe(viewLifecycleOwner, {
            when(it){
                is AuthProgress.Loading ->{
                    binding.createUserBtn.visibility = GONE
                    binding.progressBar.start()

                }

                is AuthProgress.Done ->{
                    binding.createUserBtn.visibility = VISIBLE
                    binding.progressBar.stop()
                }

                is AuthProgress.Error -> {
                    binding.createUserBtn.visibility = VISIBLE
                    binding.progressBar.stop()
                    it.message?.let { it1 -> Snackbar.make(requireView().rootView, it1, Snackbar.LENGTH_LONG).show() }
                }
            }
        })
    }

    private fun valid(): Boolean {
        return (binding.firstNameEdtxt.text?.isNotEmpty()!!
                && binding.lastNameEdtxt.text?.isNotEmpty()!!
                && Patterns.EMAIL_ADDRESS.matcher(binding.emailEdtxt.text.toString()).matches()
                && binding.userDeptEdtxt.text?.isNotEmpty()!!
                && binding.userFacultyEdtxt.text?.isNotEmpty()!!
                && binding.userCoursesEdtxt.text?.isNotEmpty()!!
                && binding.passwordEdtxt.text?.isNotEmpty()!!)

    }

}