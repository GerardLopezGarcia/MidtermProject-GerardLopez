package com.ironhack.controller.dto;

public class EmployeeDepartmentDTO {
    private String department;

    public String getDepartment() {
        return department;
    }

    public EmployeeDepartmentDTO() {
    }

    public EmployeeDepartmentDTO(String department) {
        this.department = department;
    }
}
