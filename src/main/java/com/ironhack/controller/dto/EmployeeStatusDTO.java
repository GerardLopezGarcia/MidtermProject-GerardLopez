package com.ironhack.controller.dto;

import com.ironhack.model.Status;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class EmployeeStatusDTO {
    @Enumerated(EnumType.STRING)
    private Status status;

    public Status getStatus() {
        return status;
    }
}
