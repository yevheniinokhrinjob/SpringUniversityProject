package com.nokhrin.service;

import com.nokhrin.domain.Doctor;


import java.util.List;

public interface DoctorService {


	void addDoctor(Doctor doctor);

	List<Doctor> listDoctor();

	void removeDoctor(long id);

	Doctor getDoctor(long id);

}

