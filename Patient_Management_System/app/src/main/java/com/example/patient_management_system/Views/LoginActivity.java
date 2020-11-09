package com.example.patient_management_system.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.patient_management_system.Models.Nurse;
import com.example.patient_management_system.R;
import com.example.patient_management_system.ViewModels.NurseViewModel;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText nurseId;
    private EditText nursePassword;
    private NurseViewModel nurseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nurseId = findViewById(R.id.nurseIdEditText);
        nursePassword = findViewById(R.id.passwordEditText);

        nurseViewModel = ViewModelProviders.of(this).get(NurseViewModel.class);
        nurseViewModel.allNurses().observe(this, new Observer<List<Nurse>>() {
            @Override
            public void onChanged(List<Nurse> nurses) {
            }
        });
    }

    public void signIn(View view) {

        if (nurseId.getText().toString().matches("") || nursePassword.getText().toString().matches(""))
        {
            Toast.makeText(this, "Please enter all information.", Toast.LENGTH_LONG).show();
        }

        else if (nurseViewModel.checkLogin(Integer.valueOf(nurseId.getText().toString()), nursePassword.getText().toString())) {
            SharedPreferences sharedPreferences = getSharedPreferences("NurseID", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("NurseID", nurseId.getText().toString());
            editor.commit();

            Intent intent = new Intent(this, PatientActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "ID or password is not matched.", Toast.LENGTH_LONG).show();
        }
    }

}
