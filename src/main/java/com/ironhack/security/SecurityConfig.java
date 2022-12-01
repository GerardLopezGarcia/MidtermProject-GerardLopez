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
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic();
        http.csrf().disable();
        http.authorizeHttpRequests()
                //USERS - GET // ADMIN -GET and POST
                .antMatchers(HttpMethod.GET,"/patients").hasAnyAuthority("ROLE_CONTRIBUTOR","ROLE_ADMIN")
                .antMatchers(HttpMethod.POST,"/patients").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/employees").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers(HttpMethod.POST,"/employees").hasAnyAuthority("ROLE_ADMIN")
                //USERS AND ADMINS PUT patients
                .antMatchers(HttpMethod.PUT,"/patients/{id}").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                //ADMINS PATCH employees
                .antMatchers(HttpMethod.PATCH,"/employees/status/{employee_id}").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.PATCH,"/employees/department/{employee_id}").hasAnyAuthority("ROLE_ADMIN")
                //DELETE -SOLO ADMIN
                .antMatchers(HttpMethod.DELETE,"/employees/{employee_id}").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE,"/patients/{id}").hasAnyAuthority("ROLE_ADMIN","ROLE_CONTRIBUTOR");

//                .antMatchers("/teachers").authenticated()
//                .anyRequest().permitAll();

    }
}
