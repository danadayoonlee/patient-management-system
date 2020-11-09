package com.example.patient_management_system.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.patient_management_system.Models.Patient;
import com.example.patient_management_system.Models.PatientRepository;

import java.util.List;

public class PatientViewModel extends AndroidViewModel {
    private PatientRepository patientRepository;

    public PatientViewModel(@NonNull Application application) {
        super(application);
        this.patientRepository = new PatientRepository(application);
    }

    public void insert(Patient patient) {
        patientRepository.insert(patient);
    }

    public void update(Patient patient) {
        patientRepository.update(patient);
    }

    public void delete(Patient patient) {
        patientRepository.delete(patient);
    }

    public List<Patient> allPatients() {
        return patientRepository.getAllPatients();
    }

    public Patient getPatientById(int patientId) {
        return patientRepository.getPatientById(patientId);
    }

}
