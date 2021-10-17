package com.example.annotationManager;

import org.springframework.stereotype.Component;

@Component
public class EmployeeManagerImpl implements EmployeeManager {

    @Override
    public String create() {
        return "new Manager";
    }
}
