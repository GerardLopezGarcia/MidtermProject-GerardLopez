package com.ironhack.controller.interfaces;

import com.ironhack.model.Checking;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICheckingController {
    public void saveCheckingAccount( Checking checking);
}
