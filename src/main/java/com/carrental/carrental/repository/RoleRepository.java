package com.carrental.carrental.repository;

import com.carrental.carrental.auth.ERole;
import com.carrental.carrental.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

}
