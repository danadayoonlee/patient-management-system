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
import com.example.patient_management_system.R;
import com.example.patient_management_system.ViewModels.PatientViewModel;

import java.util.ArrayList;
import java.util.List;

public class PatientActivity extends AppCompatActivity {

    private PatientViewModel patientViewModel;
    private List<Patient> patientList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        patientList = new ArrayList<Patient>();
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        patientList = patientViewModel.allPatients();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, patientList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView patientName = view.findViewById(android.R.id.text1);
                TextView patientInfo = view.findViewById(android.R.id.text2);

                patientName.setTextSize(16);
                patientName.setText(patientList.get(position).getPatientId() + " - " + patientList.get(position).getFirstName() + " " + patientList.get(position).getLastName());

                patientInfo.setText("Department: " + patientList.get(position).getDepartment() + " | Room: " + patientList.get(position).getRoom() + " | Nurse ID: " + patientList.get(position).getNurseId());

                return view;
            }
        };

        listView = findViewById(R.id.patientListView);
        listView.setAdapter(arrayAdapter);

        // When you click each item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Pick out patientId only
                TextView textView = view.findViewById(android.R.id.text1);
                String[] str = textView.getText().toString().split("-");
                int patientId = Integer.valueOf(str[0].trim());

                // Go to PatientHistoryActivity
                Intent intent = new Intent(getApplicationContext(), UpdateInfoActivity.class);
                intent.putExtra("patientId", patientId);
                startActivity(intent);
            }
        });
    }

    public void AddPatient(View view) {
        Intent intent = new Intent(this, AddPatientActivity.class);
        startActivity(intent);
    }

}
