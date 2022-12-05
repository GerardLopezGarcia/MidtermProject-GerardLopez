package com.ironhack.controller.dto;

import javax.validation.constraints.NotEmpty;

public class UserDTO {
    //validations + pasar todas las cosas que necesite
    @NotEmpty(message = "Introduzca un nombre porfavor")
    private String name;
    @NotEmpty(message = "Introduzca su contraseña porfavor")
    private String password;

    public UserDTO() {
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
