package com.security.jwt.repository;

import com.security.jwt.modele.utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUser extends JpaRepository<utilisateur, Long> {
    utilisateur findByNom(String nom);
}
