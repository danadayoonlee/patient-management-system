package com.example.patient_management_system.Models;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NurseRepository {

    private NurseDao nurseDao;
    private LiveData<List<Nurse>> allNurses;

    public NurseRepository(Context context) {
        AppDatabase db = AppDatabase.getAppDatabase(context);
        nurseDao = db.nurseDao();
        allNurses = nurseDao.getAllNurses();
    }

    public void insert(final Nurse nurse) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                nurseDao.insert(nurse);
            }
        }).start();
    }

    public void update(final Nurse nurse) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                nurseDao.update(nurse);
            }
        }).start();
    }

    public void delete(final Nurse nurse) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                nurseDao.delete(nurse);
            }
        }).start();
    }

    public LiveData<List<Nurse>> allNurses() {
        return allNurses;
    }

}
