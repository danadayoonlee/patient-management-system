package com.example.patient_management_system.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.patient_management_system.Models.Nurse;
import com.example.patient_management_system.R;
import com.example.patient_management_system.ViewModels.NurseViewModel;

public class SignUpActivity extends AppCompatActivity {

    private EditText firstName, lastName, department, password, nurseId;
    private NurseViewModel nurseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstName = findViewById(R.id.fNameEditText);
        lastName = findViewById(R.id.depEditText);
        department = findViewById(R.id.depEditText);
        password = findViewById(R.id.passwordEditText);
        nurseId = findViewById(R.id.nurseIdEditText);

        nurseViewModel = ViewModelProviders.of(this).get(NurseViewModel.class);
    }

    public void signUp(View view) {
        try {
                Nurse nurse = new Nurse();

                nurse.setNurseId(Integer.valueOf(nurseId.getText().toString()));
                nurse.setFirstName(firstName.getText().toString());
                nurse.setLastName(lastName.getText().toString());
                nurse.setDepartment(department.getText().toString());
                nurse.setPassword(password.getText().toString());

                nurseViewModel.insert(nurse);
                Toast.makeText(this,"Welcome! You've been successfully added.", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

        } catch (Exception ex) {
            Toast.makeText(this,"Please enter all information.", Toast.LENGTH_LONG).show();
        }
    }
}
