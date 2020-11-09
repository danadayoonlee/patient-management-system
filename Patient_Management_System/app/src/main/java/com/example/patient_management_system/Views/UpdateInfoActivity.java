package com.example.patient_management_system.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.patient_management_system.Models.Patient;
import com.example.patient_management_system.Models.Test;
import com.example.patient_management_system.R;
import com.example.patient_management_system.ViewModels.PatientViewModel;
import com.example.patient_management_system.ViewModels.TestViewModel;

import java.util.List;

public class UpdateInfoActivity extends AppCompatActivity {

    private int patientId;
    private PatientViewModel patientViewModel;
    private TestViewModel testViewModel;
    private Patient currentPatient;

    private TextView firstName, lastName, department, room;
    private List<Test> testList;
    private ListView testListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        patientId = getIntent().getIntExtra("patientId", 0);

        currentPatient = patientViewModel.getPatientById(patientId);

        firstName = findViewById(R.id.fNameOutputTextView);
        firstName.setText(currentPatient.getFirstName());
        lastName = findViewById(R.id.lNameOutputTextView);
        lastName.setText(currentPatient.getLastName());
        department = findViewById(R.id.depOutputTextView);
        department.setText(currentPatient.getDepartment());
        room = findViewById(R.id.roomOutputTextView);
        room.setText(String.valueOf(currentPatient.getRoom()));

        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        testList = testViewModel.getTestsByPatientId(patientId);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, testList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView testLine1 = view.findViewById(android.R.id.text1);

                testLine1.setTextSize(18);
                testLine1.setText("- Test ID: " + testList.get(position).getTestId() + ", Nurse ID: " + testList.get(position).getNurseId());

                return view;
            }
        };

        testListView = findViewById(R.id.testListView);
        testListView.setAdapter(arrayAdapter);

        testListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TextView textView = view.findViewById(android.R.id.text1);
                int testId = Integer.valueOf(textView.getText().toString().substring(11, 12));

                Intent intent = new Intent(getApplicationContext(), ViewTestInfoActivity.class);
                intent.putExtra("testId", testId);
                startActivity(intent);
            }
        });
    }

    public void addTest(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        intent.putExtra("patientId", patientId);
        startActivity(intent);
    }

    public void editPatient(View view) {
        Intent intent = new Intent(this, UpdatePatientActivity.class);
        intent.putExtra("patientId", patientId);
        startActivity(intent);
    }
}
