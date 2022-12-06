package com.ironhack.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserSecurityService userSecurityService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic();
        http.csrf().disable();
        http.authorizeHttpRequests()

                //GET
                .antMatchers(HttpMethod.GET,"/checkings").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/accounts").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/studentcheckings").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/savings").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/mysavingsaccount/{id}").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                .antMatchers(HttpMethod.GET,"/creditcards").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/mycreditaccount/{id}").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                .antMatchers(HttpMethod.GET,"/myaccounts/{name}/{password}").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")

                .antMatchers(HttpMethod.GET,"/accountholders").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/users").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/admins").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/thirdpartyusers").hasAnyAuthority("ROLE_ADMIN")

                //POST
                .antMatchers(HttpMethod.POST,"/checkings").hasAnyAuthority("ROLE_ADMIN")

                .antMatchers(HttpMethod.POST,"/accountholders").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.POST,"/users").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.POST,"/admins").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.POST,"/thirdpartyusers").hasAnyAuthority("ROLE_ADMIN")

                //PATCH
                .antMatchers(HttpMethod.PATCH,"/transfer").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                .antMatchers(HttpMethod.PATCH,"/thirdpartyusers/{hashedKey}").hasAnyAuthority("ROLE_ADMIN","ROLE_CONTRIBUTOR")
                //DELETE
                .antMatchers(HttpMethod.DELETE,"/checkings/{id}").hasAnyAuthority("ROLE_ADMIN")

                .antMatchers(HttpMethod.DELETE,"/accountholders").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE,"/admins/{name}").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE,"/thirdpartyusers/{name}").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE,"/accounts/balance/{id}").hasAnyAuthority("ROLE_ADMIN");





    }
}
