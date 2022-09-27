package com.usuario.service.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usuario.service.entidades.Usuario;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long> {

	
}
