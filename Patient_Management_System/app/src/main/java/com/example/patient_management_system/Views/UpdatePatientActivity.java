package com.example.patient_management_system.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.patient_management_system.Models.Patient;
import com.example.patient_management_system.R;
import com.example.patient_management_system.ViewModels.PatientViewModel;

public class UpdatePatientActivity extends AppCompatActivity {

    private EditText firstName, lastName, department, room;
    private Patient patient;
    private PatientViewModel patientViewModel;
    private int patientId;
    private Patient currentPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient);

        firstName =findViewById(R.id.fNameEditText);
        lastName =findViewById(R.id.lNameEditText);
        department =findViewById(R.id.depEditText);
        room =findViewById(R.id.roomEditText);

        int patientId = getIntent().getIntExtra("patientId", 0);

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        patient = patientViewModel.getPatientById(patientId);

        // Show original data
        firstName.setText(patient.getFirstName());
        lastName.setText(patient.getLastName());
        department.setText(patient.getDepartment());
        room.setText(String.valueOf(patient.getRoom()));
    }

    public void savePatient(View view)
    {
        patient.setFirstName(firstName.getText().toString());
        patient.setLastName(lastName.getText().toString());
        patient.setDepartment(department.getText().toString());
        patient.setRoom(room.getText().toString());

        patientViewModel.update(patient);
        Toast.makeText(this, "Patient information updated.", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, UpdateInfoActivity.class);
        intent.putExtra("patientId", patient.getPatientId());
        startActivity(intent);
    }

    public void deletePatient(View view){

        patientId = getIntent().getIntExtra("patientId", 0);
        currentPatient = patientViewModel.getPatientById(patientId);
        patientViewModel.delete(currentPatient);

        Intent intent = new Intent(this,PatientActivity.class);
        startActivity(intent);
        finish();

        Toast.makeText(this,"Patient " + patientId + " is deleted.", Toast.LENGTH_LONG).show();
    }

}
