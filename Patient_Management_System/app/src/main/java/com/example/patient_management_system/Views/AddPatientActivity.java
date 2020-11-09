package com.example.patient_management_system.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.patient_management_system.Models.Patient;
import com.example.patient_management_system.R;
import com.example.patient_management_system.ViewModels.PatientViewModel;

public class AddPatientActivity extends AppCompatActivity {

    private EditText firstName, lastName, department, room;
    private PatientViewModel patientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        firstName = findViewById(R.id.fNameEditText);
        lastName = findViewById(R.id.depEditText);
        department = findViewById(R.id.depEditText);
        room = findViewById(R.id.roomEditText);
    }

    public void addNewPatient(View view) {
        Patient patient = new Patient();

        patient.setFirstName(firstName.getText().toString());
        patient.setLastName(lastName.getText().toString());
        patient.setDepartment(department.getText().toString());
        patient.setRoom(room.getText().toString());

        // Put nurseId automatically
        SharedPreferences sharedPreferences = getSharedPreferences("NurseID", MODE_PRIVATE);
        patient.setNurseId(Integer.valueOf(sharedPreferences.getString("NurseID", "")));

        patientViewModel.insert(patient);
        startActivity(new Intent(this, PatientActivity.class));
    }
}
