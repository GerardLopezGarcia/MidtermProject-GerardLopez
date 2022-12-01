package com.ironhack.controller.Interfaces;

import com.ironhack.controller.dto.EmployeeDepartmentDTO;
import com.ironhack.controller.dto.EmployeeStatusDTO;
import com.ironhack.model.Employee;
import com.ironhack.model.Status;

import java.util.List;

public interface IEmployeeController {
    Employee getEmployeeById(Integer employee_id);
    List<Employee> getEmployeesByStatus(Status status);
    List<Employee> getEmployeesByDepartment(String department);

    void saveEmployee( Employee employee);

    //Patch
    void updateEmployeeStatus(EmployeeStatusDTO employeeStatusDTO,Integer employee_id);
    void updateEmployeeDepartment(EmployeeDepartmentDTO employeeDepartmentDTO,Integer employee_id);
}
