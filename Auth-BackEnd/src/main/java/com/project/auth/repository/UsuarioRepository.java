package com.project.auth.repository;

import com.project.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

}
