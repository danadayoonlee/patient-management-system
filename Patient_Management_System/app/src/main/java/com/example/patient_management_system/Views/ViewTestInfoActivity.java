package com.example.patient_management_system.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.patient_management_system.Models.Test;
import com.example.patient_management_system.R;
import com.example.patient_management_system.ViewModels.TestViewModel;

public class ViewTestInfoActivity extends AppCompatActivity {

    private TestViewModel testViewModel;
    private TextView BPL, BPH, temprature, bloodPressure, urinalysis, testId, nurseId;

    private int currentTestId;
    private Test test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test_info);

        currentTestId = getIntent().getIntExtra("testId", 0);
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        test = testViewModel.getTestById(currentTestId);

        BPL = findViewById(R.id.bplOutputTextView);
        BPH = findViewById(R.id.bpmOutputTextView);
        temprature = findViewById(R.id.tempOutputTextView);
        bloodPressure = findViewById(R.id.bloodOutputTextView);
        urinalysis = findViewById(R.id.urinalysisOutputTextView);
        testId = findViewById(R.id.testIdOutputTextView);
        nurseId = findViewById(R.id.nurseIdOutputTextView);
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);

        BPL.setText(test.getBPL());
        BPH.setText(test.getBPH());
        temprature.setText(String.valueOf(test.getTemperature()));
        bloodPressure.setText(String.valueOf(test.getBloodPressure()));
        urinalysis.setText(test.getUrinalysis());
        testId.setText(String.valueOf(test.getTestId()));
        nurseId.setText(String.valueOf(test.getNurseId()));
    }

    public void deleteTest(View view) {
        testViewModel.delete(test);

        Intent intent = new Intent(this, UpdateInfoActivity.class);
        intent.putExtra("patientId", test.getPatientId());
        startActivity(intent);

        Toast.makeText(this,"Test " + currentTestId + " is deleted.", Toast.LENGTH_LONG).show();
    }

}
