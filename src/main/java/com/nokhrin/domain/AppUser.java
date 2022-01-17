package com.nokhrin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String fName;
    @NotNull
    private String lName;
    @NotNull
    private int pesel;
    @NotNull
    private String email;

    private boolean enabled;
    @NotNull
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private Set<Visit> visits;

    @OneToOne(cascade=CascadeType.ALL)
    private Address address;

    @NotNull
    private String password;

    @Column(unique = true)
    private String login;

    @JsonIgnore
    @ManyToMany( fetch = FetchType.EAGER)
    private Set<UserRoles> appUserRole = new HashSet<UserRoles>(0);

    @OneToOne(mappedBy = "appUser",orphanRemoval=true, cascade = CascadeType.ALL)
    private Doctor doctor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRoles> getAppUserRole() {
        return appUserRole;
    }

    public void setAppUserRole(Set<UserRoles> appUserRole) {
        this.appUserRole = appUserRole;
    }


    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
