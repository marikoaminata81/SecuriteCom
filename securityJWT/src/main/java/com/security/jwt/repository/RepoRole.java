package com.security.jwt.repository;

import com.security.jwt.modele.role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoRole extends JpaRepository<role,Long> {
    role findByRolename(String nom);
}
