package com.ironhack.repository;

import com.ironhack.model.Employee;
import com.ironhack.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
//    Optional<Employee>findById(Integer employee_id);
    List<Employee>findAllByStatus(Status status);
    List<Employee>findAllByDepartment(String department);
    Optional<Employee>findById(Integer id);

}
