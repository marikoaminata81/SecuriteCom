package com.example.demo.Repository;

import com.example.demo.Modeles.Role;
import com.example.demo.Modeles.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByNom(String nom);
}
