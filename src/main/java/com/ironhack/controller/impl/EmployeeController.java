package com.ironhack.controller.impl;

import com.ironhack.controller.Interfaces.IEmployeeController;
import com.ironhack.controller.dto.EmployeeDepartmentDTO;
import com.ironhack.controller.dto.EmployeeStatusDTO;
import com.ironhack.model.Employee;
import com.ironhack.model.Status;
import com.ironhack.repository.EmployeeRepository;
import com.ironhack.service.impl.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController implements IEmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeService employeeService;

    //Get

    @GetMapping("/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }


    @GetMapping("/employees/employee_id")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@RequestParam Integer employee_id) {
        return employeeService.getEmployeeById(employee_id);
    }


    @GetMapping("/employees/status")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployeesByStatus(@RequestParam(defaultValue = "ON") Status status){
        return employeeRepository.findAllByStatus(status);
    }
    @GetMapping("/employees/department")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployeesByDepartment(@RequestParam String department){
        return employeeService.getEmployeesByDepartment(department);
    }

    //POST

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveEmployee(@RequestBody Employee employee){
        employeeRepository.save(employee);
    }

    //PATCH
    @PatchMapping("/employees/status/{employee_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployeeStatus(@RequestBody EmployeeStatusDTO employeeStatusDTO, @PathVariable  Integer employee_id) {
        employeeService.updateEmployeeStatus(employeeStatusDTO.getStatus(),employee_id);
    }

    @PatchMapping("/employees/department/{employee_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployeeDepartment(@RequestBody  EmployeeDepartmentDTO employeeDepartmentDTO, @PathVariable Integer employee_id) {
        employeeService.updateEmployeeDepartment(employeeDepartmentDTO.getDepartment(),employee_id);
    }
    //DELETE
    @DeleteMapping("/employees/{employee_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Integer employee_id){
        employeeService.deleteCourse(employee_id);
    }

}
