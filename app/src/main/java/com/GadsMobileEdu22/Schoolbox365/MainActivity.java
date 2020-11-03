package com.GadsMobileEdu22.Schoolbox365;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.GadsMobileEdu22.Schoolbox365.admin.ui.dashboard.AdminDashBoardActivity;
import com.GadsMobileEdu22.Schoolbox365.databinding.ActivityMainBinding;
import com.GadsMobileEdu22.Schoolbox365.lecturers.ui.dashboard.LecturersDashBoardActivity;
import com.GadsMobileEdu22.Schoolbox365.students.ui.dashboard.StudentsDashBoardActivity;
import com.google.android.material.snackbar.Snackbar;

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
                navigate();
            }
        });

        viewModel.getProgress().observe(this, authenticationProgress -> {
            switch (authenticationProgress){
                case Loading:
                    binding.loginBtn.setVisibility(View.GONE);
                    binding.progressBar.start();
                    break;
//       Moved this code to just before the dashboard opens to improve experience.
//                case Done:
//                    binding.loginBtn.setVisibility(View.VISIBLE);
//                    binding.progressBar.stop();
//                    break;
                case AuthError:
                    binding.progressBar.stop();
                    binding.loginBtn.setVisibility(View.VISIBLE);
                    viewModel.getMessage().observe(this, message -> Snackbar.make(binding.getRoot(), message,Snackbar.LENGTH_LONG).show());


            }
        });
        setContentView(binding.getRoot());
    }

    private void navigate() {
        viewModel.getCurrentUser().observe(this, currentUser ->{
            if (currentUser != null) {

                binding.loginBtn.setVisibility(View.VISIBLE);
                binding.progressBar.stop();

                if (currentUser.getUserType().equals("Student")) {

                    Snackbar.make(binding.getRoot(),"Welcome " + currentUser.getName(),Snackbar.LENGTH_LONG).show();
                    Intent intent = new Intent(this, StudentsDashBoardActivity.class);
                    intent.putExtra("usersName", currentUser.getName());
                    startActivity(intent);
                }  else if (currentUser.getUserType().equals("lecturers")) {
                    Intent intent = new Intent(this, LecturersDashBoardActivity.class);
                    startActivity(intent);
                }
                else if (currentUser.getUserType().equals("admin")) {
                    Intent intent = new Intent(this, AdminDashBoardActivity.class);
                    intent.putExtra("usersName", currentUser.getName());
                    startActivity(intent);
                }
                finish();
            }
        });
    }

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