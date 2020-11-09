package com.example.patient_management_system.Models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PatientDao {

    @Query("select * from Patient")
    List<Patient> getAllPatients();

    @Query("select * from Patient where patientId = :patientId")
    Patient getPatientById(int patientId);

    @Insert
    void insert(Patient patient);

    @Update
    void update(Patient patient);

    @Delete
    void delete(Patient patient);

}
