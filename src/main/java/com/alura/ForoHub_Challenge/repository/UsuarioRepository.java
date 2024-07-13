package com.alura.ForoHub_Challenge.repository;

import com.alura.ForoHub_Challenge.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {



    UserDetails findByNombre(String username);
}
