package com.nokhrin.service;

import java.sql.Time;
import java.util.List;


import com.nokhrin.domain.AppUser;
import com.nokhrin.domain.UserRoles;
import com.nokhrin.domain.Visit;

public interface UserService {


	void addAppUser(AppUser appUser);
	void editAppUser(AppUser appUser);
	void editAppUserPassword(AppUser appUser);
	List<AppUser> listAppUser();
	void removeAppUser (long id);
	AppUser getAppUser(long id);
	void addRoleToAppUser(AppUser appUser,String role);
	AppUser findByLogin(String login);
	void removeAppUserRole(AppUser appUser,String appUserRole);

}

