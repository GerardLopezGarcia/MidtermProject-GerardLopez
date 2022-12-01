package com.ironhack.model;



import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    private Integer employee_id;
    private String department;
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Employee(Integer employee_id, String department, String name, Status status) {
        this.employee_id = employee_id;
        this.department = department;
        this.name = name;
        this.status = status;
    }

    public Employee() {
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", department='" + department + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
