package com.ironhack.controller.impl;

import com.ironhack.controller.dto.BalanceDTO;
import com.ironhack.controller.interfaces.IUsersController;
import com.ironhack.model.*;
import com.ironhack.repository.*;
import com.ironhack.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UsersController implements IUsersController {
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    UserService userService;

    /* Todas las rutas de usuarios se manejarán desde el controlador UserController
     los administradores serán los únicos que podrán acceder a esta información */

    //GET
    @GetMapping("/accountholders")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAccounts(){
        return accountHolderRepository.findAll();
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/admins")
    @ResponseStatus(HttpStatus.OK)
    public List<Admin> getAllAdmins(){
        return adminRepository.findAll();
    }

    @GetMapping("/thirdpartyusers")
    @ResponseStatus(HttpStatus.OK)
    public List<ThirdParty> getAllThirdPartyUsers(){
        return thirdPartyRepository.findAll();
    }

    //POST
    @PostMapping("/accountholders")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void saveAccountHolders(@RequestBody @Valid AccountHolder accountHolder){
        accountHolderRepository.save(accountHolder);
    }
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void saveUsers(@RequestBody @Valid User user){
        userRepository.save(user);
    }

    @PostMapping("/admins")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void saveAdmins(@RequestBody @Valid Admin admin){
        adminRepository.save(admin);
    }

    @PostMapping("/thirdpartyusers")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void saveThirdPartyUsers(@RequestBody @Valid ThirdParty thirdParty){
        thirdPartyRepository.save(thirdParty);
    }
    //DELETE
    @DeleteMapping("/accountholders/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountHolder(@PathVariable String name){
        userService.deleteAccountHolder(name);
    }

    @DeleteMapping("/admins/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdmin(@PathVariable String name){
        userService.deleteAdmin(name);
    }

    @DeleteMapping("/thirdpartyusers/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteThirdPartyUser(@PathVariable String name){
        userService.deleteThirdPartyUser(name);
    }


    //PATCH- acceder y modificar balance de cualquier cuenta
    @PatchMapping("/accounts/balance/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAccountBalance(@RequestBody BalanceDTO balanceDTO, @PathVariable  Integer id) {
        userService.updateAccountBalance(balanceDTO.getAmount(),id);
    }


}
