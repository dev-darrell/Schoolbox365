package com.GadsMobileEdu22.Schoolbox365;


import android.os.Bundle;
import android.util.Patterns;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.GadsMobileEdu22.Schoolbox365.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);


        binding.loginBtn.setOnClickListener(v -> {
            if (valid()){
                viewModel.signIn(Objects.requireNonNull(binding.regNoEditTxt.getText()).toString(), Objects.requireNonNull(binding.passwordEditTxt.getText()).toString()
                );
            }
        });

        viewModel.getUser().observe(this, user ->{
            if (user != null){

//                navigate();



            }
        });
    }

//    private void navigate() {
//        viewModel.getCurrentUser().observe(this, currentUser ->{
//            if (currentUser != null) {
//                if (currentUser.getUserType().equals("Student")) {
//                    Intent intent = new Intent(this, StudentsDashBoardActivity.class);
//                    startActivity(intent);
//                }  else if (currentUser.getUserType().equals("lecturers")) {
//                    Intent intent = new Intent(this, StudentsDashBoardActivity.class);
//                    startActivity(intent);
//                }
//                else if (currentUser.getUserType().equals("admin")) {
//                    Intent intent = new Intent(this, AdminDashBoardActivity.class);
//                    startActivity(intent);
//                }
//
//            }
//        });
//    }

    private boolean valid() {
        if (binding.regNoEditTxt.getText() != null){
            if (Patterns.EMAIL_ADDRESS.matcher(binding.regNoEditTxt.getText()).matches() && binding.passwordEditTxt.getText() != null){
                return true;
            }else {
                binding.regNoTxtLayout.setError("Please put in a valid email");
                return false;
            }
        }else if(binding.passwordEditTxt.getText() != null){
            if (binding.passwordEditTxt.getText().toString().length() > 6 && binding.regNoEditTxt.getText() != null){
                return true;
            }else {
                binding.passwordTxtLayout.setError("Password is too short");
                return false;
            }
        }
        return false;
    }


}