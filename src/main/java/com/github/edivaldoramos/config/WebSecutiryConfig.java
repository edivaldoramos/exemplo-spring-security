package com.github.edivaldoramos.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecutiryConfig extends WebSecurityConfigurerAdapter {
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //CONFIGURANDO USUARIO EM MEMORIA
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("neo")
                .password(passwordEncoder().encode("matrix"))
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //USUARIO TERA QUE EFETUAR LOGIN E SO TERÁ ACESSO REF A ENDPOINT DE EMPRESAS
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/empresas/**").hasRole("USER")
                    .antMatchers("/api/filiais/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }
}
