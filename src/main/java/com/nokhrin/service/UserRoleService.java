package com.nokhrin.service;

import com.nokhrin.domain.UserRoles;
import java.util.List;

public interface UserRoleService {

    void addAppUserRole(UserRoles appUserRole);
    List<UserRoles> listAppUserRole();
    UserRoles getAppUserRole(String role);

}

