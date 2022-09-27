package com.carro.service.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carro.service.entidades.Carro;

@Repository
public interface CarroRepo extends JpaRepository<Carro, Long> {
	List<Carro> findByUsuarioId(Long usuarioId);
}
