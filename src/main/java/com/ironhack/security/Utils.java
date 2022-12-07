package com.ironhack.security;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utils {
    public static void main(String[] args) {
        String password1 = passwordEncoder("ironhack");
        String password2 = passwordEncoder("ironhack1");
        String password3 = passwordEncoder("ironhack2");
        String password4 = passwordEncoder("ironhack3");
        String password5 = passwordEncoder("ironhack4");
        String password6 = passwordEncoder("ironhack5");
        String password7 = passwordEncoder("Administrador1");
        String password8 = passwordEncoder("Administrador2");
        String password9 = passwordEncoder("UsuarioExterno1");
        String password10 = passwordEncoder("UsuarioExterno2");
        String password11 = passwordEncoder("UsuarioExterno3");

        System.out.println(password1);
        System.out.println(password2);
        System.out.println(password3);
        System.out.println(password4);
        System.out.println(password5);
        System.out.println(password6);
        System.out.println(password7);
        System.out.println(password8);
        System.out.println(password9);
        System.out.println(password10);
        System.out.println(password11);

    }
    static String passwordEncoder(String rawPassword){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(rawPassword);
    }
}