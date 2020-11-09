package com.example.patient_management_system.Models;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class TestRepository {

    private TestDao testDao;

    public TestRepository(Context context) {
        AppDatabase db = AppDatabase.getAppDatabase(context);
        testDao = db.testDao();
    }

    public void insert(final Test test) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                testDao.insert(test);
            }
        }).start();
    }

    public void update(final Test test) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                testDao.update(test);
            }
        }).start();
    }

    public void delete(final Test test) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                testDao.delete(test);
            }
        }).start();
    }

    public List<Test> getAllTests() {
        try {
            return new getAllTestsAsync().execute().get();
        } catch (Exception ex) {
            Log.e("Error: ", ex.getMessage());
        }
        return null;
    }

    private class getAllTestsAsync extends AsyncTask<Void, Void, List<Test>> {
        @Override
        protected List<Test> doInBackground(Void... voids) {
            try {
                return testDao.getAllTests();
            } catch (Exception ex) {
                Log.e("Error: ", ex.getMessage());
            }
            return null;
        }
    }

    public Test getTestById(int testId) {

        try {
            return new getTestByIdAsync().execute(testId).get();
        } catch (Exception ex) {
            Log.e("Error: ", ex.getMessage());
        }
        return null;
    }

    private class getTestByIdAsync extends AsyncTask<Integer, Void, Test> {
        @Override
        protected Test doInBackground(Integer... params) {
            try {
                return testDao.getTestById(params[0]);
            } catch (Exception ex) {
                Log.e("Error: ", ex.getMessage());
            }
            return null;
        }
    }

    public List<Test> getTestsByPatientId(int patientId) {
        try {
            return new getTestsByPatientIdAsync().execute(patientId).get();
        } catch (Exception ex) {
            Log.e("Error: ", ex.getMessage());
        }
        return null;
    }

    private class getTestsByPatientIdAsync extends AsyncTask<Integer, Void, List<Test>> {
        @Override
        protected List<Test> doInBackground(Integer... params) {
            try {
                return testDao.getTestsByPatientId(params[0]);
            } catch (Exception ex) {
                Log.e("Error: ", ex.getMessage());
            }
            return null;
        }
    }

}