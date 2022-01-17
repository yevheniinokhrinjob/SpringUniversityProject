package com.nokhrin.domain;

import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
public class VerificationToken {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = AppUser.class, fetch = FetchType.EAGER)
    private AppUser appUser;

}
