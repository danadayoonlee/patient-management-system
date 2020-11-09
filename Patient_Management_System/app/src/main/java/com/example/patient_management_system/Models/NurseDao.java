package com.example.patient_management_system.Models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NurseDao {

    @Query("select * from Nurse")
    LiveData<List<Nurse>> getAllNurses();

    @Insert
    void insert(Nurse nurse);

    @Update
    void update(Nurse nurse);

    @Delete
    void delete(Nurse nurse);

}
