package com.example.patient_management_system.Models;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class PatientRepository {

    private PatientDao patientDao;

    public PatientRepository(Context context) {
        AppDatabase db = AppDatabase.getAppDatabase(context);
        patientDao = db.patientDao();
    }

    public void insert(final Patient patient) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                patientDao.insert(patient);
            }
        }).start();
    }

    public void update(final Patient patient) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                patientDao.update(patient);
            }
        }).start();
    }

    public void delete(final Patient patient) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                patientDao.delete(patient);
            }
        }).start();
    }

    public List<Patient> getAllPatients() {
        try {
            return new getAllPatientsAsync().execute().get();
        } catch (Exception ex) {
            Log.e("Error: ", ex.getMessage());
        }
        return null;
    }

    private class getAllPatientsAsync extends AsyncTask<Void, Void, List<Patient>> {
        @Override
        protected List<Patient> doInBackground(Void... voids) {
            try {
                return patientDao.getAllPatients();
            } catch (Exception ex) {
                Log.e("Error: ", ex.getMessage());
            }
            return null;
        }
    }

    public Patient getPatientById(int patientId) {
        try {
            return new getPatientByIdAsync().execute(patientId).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private class getPatientByIdAsync extends AsyncTask<Integer, Void, Patient> {
        @Override
        protected Patient doInBackground(Integer... params) {
            try {
                return patientDao.getPatientById(params[0]);
            } catch (Exception ex) {
                Log.e("Error: ", ex.getMessage());
            }
            return null;
        }
    }

}
