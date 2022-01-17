package com.nokhrin.dao;

import com.nokhrin.domain.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserRoleRepository extends JpaRepository<UserRoles, Long> {
    UserRoles findByRole(String role);
}



