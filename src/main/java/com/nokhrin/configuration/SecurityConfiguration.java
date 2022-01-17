package com.nokhrin.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource(name="myAppUserDetailsService")
    private UserDetailsService userDetailsService;


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {

 //    auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("USER");
  //      auth.inMemoryAuthentication().withUser("kapj").password("{noop}kapj").roles("USER");
  //      auth.inMemoryAuthentication().withUser("student").password("{noop}student").roles("STUDENT");
        //User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
     
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

     /* http.authorizeRequests()
        .antMatchers("/appUsers*").access("hasRole('ADMIN') or hasRole('USER')")
              // "hasRole('ADMIN')" is the same as "hasRole('ROLE_ADMIN')" and "hasAuthority('ROLE_ADMIN')"
        .antMatchers("/appUserRole*").access("hasRole('ROLE_ADMIN')")
        .antMatchers("/address*").access("hasRole('ADMIN') or hasRole('USER')")
        .antMatchers("/exampleOne*").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
        .antMatchers("/exampleTwo*").access("hasRole('ROLE_STUDENT')")
        .antMatchers("/exampleThree*").access("hasRole('ROLE_USER')")
        //.and().formLogin().permitAll(); // with default login page
        .and().formLogin().loginPage("/login").permitAll() // with custom login page
        .usernameParameter("login").passwordParameter("password")
        .failureForwardUrl("/login.html?error")
        .and().logout().logoutSuccessUrl("/login.html?logout")
        .and().exceptionHandling().accessDeniedPage("/accessDenied");*/
        http.authorizeRequests()

                .and().formLogin().loginPage("/login").permitAll() // with custom login page
                .usernameParameter("login").passwordParameter("password")
                .failureForwardUrl("/login.html?error")
                .and().logout().logoutSuccessUrl("/login.html?logout")
                .and().exceptionHandling().accessDeniedPage("/accessDenied");

    }
}

