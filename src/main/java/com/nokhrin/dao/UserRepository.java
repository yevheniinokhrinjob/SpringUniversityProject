package com.nokhrin.dao;

import java.util.List;
import javax.transaction.Transactional;

import com.nokhrin.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Transactional 
@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    List<AppUser> findByLName(String lastName);
    AppUser findById(long id);
    AppUser findByLogin(String lName);
    
}

