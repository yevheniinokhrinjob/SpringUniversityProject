package com.nokhrin.service;

import com.nokhrin.dao.DoctorRepository;

import com.nokhrin.domain.Doctor;
import com.nokhrin.domain.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Service
public class DoctorServiceImpl implements DoctorService{


	private DoctorRepository doctorRepository;


	@Autowired
	public DoctorServiceImpl(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;

	}

	@Transactional
	public void addDoctor(Doctor doctor) {
		doctorRepository.save(doctor);
	}

	@Transactional
	public List<Doctor> listDoctor() {
		return doctorRepository.findAll();
	}

	@Transactional
	public void removeDoctor(long id) {
		System.out.println("deleted doc" + id);
		doctorRepository.delete(id);
	}

	@Transactional
	public Doctor getDoctor(long id) {
		return doctorRepository.findById(id);
	}




}