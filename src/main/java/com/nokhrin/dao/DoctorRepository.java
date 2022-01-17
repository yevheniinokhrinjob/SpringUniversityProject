package com.nokhrin.dao;

import com.nokhrin.domain.AppUser;
import com.nokhrin.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional 
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor findById(long id);

    
}

