package com.ironhack.service.interfaces;

import com.ironhack.model.Employee;
import com.ironhack.model.Status;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IEmployeeService {
    Employee getEmployeeById(Integer id);
    List<Employee> getEmployeesByDepartment(String department);


    void updateEmployeeStatus(Status status, Integer employee_id);

    void updateEmployeeDepartment(String department, Integer employee_id);

    void deleteCourse(Integer employee_id);
}
