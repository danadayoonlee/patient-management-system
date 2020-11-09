package com.example.patient_management_system.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.patient_management_system.Models.Test;
import com.example.patient_management_system.Models.TestRepository;

import java.util.List;

public class TestViewModel extends AndroidViewModel {

    private TestRepository testRepository;

    public TestViewModel(@NonNull Application application) {
        super(application);
        this.testRepository = new TestRepository(application);
    }

    public void insert(Test test) {
        testRepository.insert(test);
    }

    public void update(Test test) {
        testRepository.update(test);
    }

    public void delete(Test test) {
        testRepository.delete(test);
    }

    public List<Test> allTests() {
        return testRepository.getAllTests();
    }

    public Test getTestById(int testId) {
        return testRepository.getTestById(testId);
    }

    public List<Test> getTestsByPatientId(int patientId) {
        return testRepository.getTestsByPatientId(patientId);
    }

}