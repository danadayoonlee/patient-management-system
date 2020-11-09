package com.example.patient_management_system.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity=Nurse.class,
                parentColumns="nurseId",
                childColumns = "nurseId")
},
        indices={@Index("nurseId")})

public class Patient {

    // Fields
    @PrimaryKey(autoGenerate = true)
    private int patientId;

    @ColumnInfo
    private String firstName, lastName, department, room;

    @ColumnInfo
    private int nurseId;

    // Getters and Setters
    public int getPatientId() { return patientId; }

    public void setPatientId(int patientId) { this.patientId = patientId; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    @Override
    public String toString() {
        return "Patient {" +
                "patientId=" + patientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department='" + department + '\'' +
                ", room=" + room +
                ", nurseId=" + nurseId +
                "}";
    }
}
