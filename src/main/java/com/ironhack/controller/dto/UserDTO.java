package com.ironhack.controller.dto;

import javax.validation.constraints.NotEmpty;

public class UserDTO {
    @NotEmpty(message = "Introduzca un nombre porfavor")
    private String name;
    @NotEmpty(message = "Introduzca su contraseña porfavor")
    private String password;
}
