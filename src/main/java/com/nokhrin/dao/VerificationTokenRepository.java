package com.nokhrin.dao;

import com.nokhrin.domain.AppUser;
import com.nokhrin.domain.UserRoles;
import com.nokhrin.domain.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findById(long id);
    VerificationToken findByToken(String token);
}
