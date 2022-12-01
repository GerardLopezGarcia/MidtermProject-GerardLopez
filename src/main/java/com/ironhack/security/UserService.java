package com.ironhack.security;

import com.ironhack.model.User;
import com.ironhack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByName(username);
        if (userOptional.isEmpty()) throw new UsernameNotFoundException("Usuario no encontrado");
        User user = userOptional.get();

        //crea una lista de roles y añade los distintos usuarios
        //con los detalles en una nueva clase userdetails q necesita nombre usuario y rol
        List<GrantedAuthority> roles =new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),roles);
        return userDetails;
    }
}
