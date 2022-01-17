package com.nokhrin.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserRoles {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String role;

    @ManyToMany(mappedBy = "appUserRole")
    private Set<AppUser> users = new HashSet<AppUser>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<AppUser> getUsers() {
        return users;
    }

    public void setUsers(Set<AppUser> users) {
        this.users = users;
    }
}
