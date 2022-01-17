package com.nokhrin.service;

import com.nokhrin.dao.UserRoleRepository;
import com.nokhrin.domain.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("appUserRoleService")
public class AppUserRoleServiceImpl implements UserRoleService {

    private UserRoleRepository appUserRoleRepository;

    @Autowired
    public AppUserRoleServiceImpl(UserRoleRepository appUserRoleRepository) {
        this.appUserRoleRepository = appUserRoleRepository;
    }

    @Transactional
    public void addAppUserRole(UserRoles appUserRole) {
        appUserRoleRepository.save(appUserRole);
    }

    @Transactional
    public List<UserRoles> listAppUserRole() {
        return appUserRoleRepository.findAll();
    }

    @Transactional
    public UserRoles getAppUserRole(String role) {
        return appUserRoleRepository.findByRole(role);
    }

}






