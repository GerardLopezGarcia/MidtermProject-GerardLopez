package com.ironhack.service.impl;

import com.ironhack.model.Employee;
import com.ironhack.model.Status;
import com.ironhack.repository.EmployeeRepository;
import com.ironhack.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;


    public Employee getEmployeeById(Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isEmpty())return null;
        return employeeOptional.get();
    }


    public List<Employee> getEmployeesByDepartment(String department) {
        List<Employee> employees =  employeeRepository.findAllByDepartment(department);
        return employees;
    }

    public void updateEmployeeStatus(Status status, Integer employee_id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee_id);
        if(optionalEmployee.isEmpty())return;
        Employee employee = optionalEmployee.get();
        employee.setStatus(status);
        employeeRepository.save(employee);
    }

    public void updateEmployeeDepartment(String department, Integer employee_id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee_id);
        if(optionalEmployee.isEmpty())return;
        Employee employee = optionalEmployee.get();
        employee.setDepartment(department);
        employeeRepository.save(employee);
    }


    public void deleteCourse(Integer employee_id) {
        Optional<Employee>optionalEmployee = employeeRepository.findById(employee_id);
        if(optionalEmployee.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The employee doesn't exist");
        employeeRepository.deleteById(employee_id);
    }

}
