package com.example.patient_management_system.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.patient_management_system.Models.Test;
import com.example.patient_management_system.R;
import com.example.patient_management_system.ViewModels.TestViewModel;

public class TestActivity extends AppCompatActivity {

    private TestViewModel testViewModel;
    private EditText BPL, BPH, temperature, bloodPressure, urinalysis;
    private int nurseId, patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("NurseID", MODE_PRIVATE);
        nurseId = Integer.valueOf(sharedPreferences.getString("NurseID", ""));
        patientId = getIntent().getIntExtra("patientId", 0);

        setContentView(R.layout.activity_test);
        BPL = findViewById(R.id.bplEditText);
        BPH = findViewById(R.id.bphEditText);
        temperature = findViewById(R.id.temperatureEditText);
        bloodPressure = findViewById(R.id.bloodPressureEditText);
        urinalysis = findViewById(R.id.urinalysisEditText);
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
    }

    public void addNewTest(View view) {

        Test test = new Test();

        test.setBPL(BPL.getText().toString());
        test.setBPH(BPH.getText().toString());
        test.setTemperature(Integer.valueOf(temperature.getText().toString()));
        test.setBloodPressure(Integer.valueOf(bloodPressure.getText().toString()));
        test.setUrinalysis(urinalysis.getText().toString());
        test.setPatientId(patientId);
        test.setNurseId(nurseId);

        try {
            testViewModel.insert(test);

            Intent intent = new Intent(this, UpdateInfoActivity.class);
            intent.putExtra("patientId", patientId);
            startActivity(intent);

        } catch (Exception ex) {
            Toast.makeText(this,"Please enter all information.", Toast.LENGTH_LONG).show();
        }
    }

}
