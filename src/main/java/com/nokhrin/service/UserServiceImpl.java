package com.nokhrin.service;

import java.io.Console;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nokhrin.dao.UserRepository;
import com.nokhrin.dao.UserRoleRepository;
import com.nokhrin.domain.AppUser;
import com.nokhrin.domain.UserRoles;
import com.nokhrin.domain.Visit;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService{


	private UserRepository appUserRepository;
	private UserRoleRepository appUserRoleRepository;

	@Autowired
	public UserServiceImpl(UserRepository appUserRepository, UserRoleRepository appUserRoleRepository) {
		this.appUserRepository = appUserRepository;
		this.appUserRoleRepository = appUserRoleRepository;
	}

	@Transactional
	public void addAppUser(AppUser appUser) {
		appUser.getAppUserRole().add(appUserRoleRepository.findByRole("ROLE_USER"));
		appUser.setPassword(hashPassword(appUser.getPassword()));
	//	System.out.println(hashPassword(appUser.getPassword())+" xcxczx");
		appUserRepository.save(appUser);
	}

	@Transactional
	public void editAppUser(AppUser appUser) {
		appUser.getAppUserRole().add(appUserRoleRepository.findByRole("ROLE_USER"));
	//	appUser.setPassword(hashPassword(appUser.getPassword()));
		appUserRepository.save(appUser);
	}
	@Transactional
	public void editAppUserPassword(AppUser appUser) {
		appUser.getAppUserRole().add(appUserRoleRepository.findByRole("ROLE_USER"));
		appUser.setPassword(hashPassword(appUser.getPassword()));
		appUserRepository.save(appUser);
	}
	private String hashPassword(String password)
	{
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	@Transactional
	public List<AppUser> listAppUser() {
		return appUserRepository.findAll();
	}

	@Transactional
	public void removeAppUser(long id) {
		appUserRepository.delete(id);
	}

	@Transactional
	public AppUser getAppUser(long id) {
		return appUserRepository.findById(id);
	}

	@Transactional
	public AppUser findByLogin(String login) {
		return appUserRepository.findByLogin(login);
	}

	@Transactional
	public void removeAppUserRole(AppUser appUser,String userRole){
	Set<UserRoles> roles =	new HashSet<UserRoles>(0);
		for (UserRoles role:appUser.getAppUserRole()
			 ) {
			if(!role.getRole().equals(userRole)){
				roles.add(role);
			}
		}

		appUser.setAppUserRole(roles);
		appUserRepository.save(appUser);
	}
	@Transactional
	public void addRoleToAppUser(AppUser appUser, String role){
		appUser.getAppUserRole().add(appUserRoleRepository.findByRole(role));
		appUserRepository.save(appUser);
	}

}