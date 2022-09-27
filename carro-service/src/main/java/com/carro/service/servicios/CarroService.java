package com.carro.service.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carro.service.entidades.Carro;
import com.carro.service.repositorio.CarroRepo;

@Service
public class CarroService {

	private CarroRepo carroRepo;

	public CarroService(CarroRepo carroRepo) {
		this.carroRepo = carroRepo;
	}

	public List<Carro> getAll() {
		return carroRepo.findAll();
	}

	public Carro getCarroById(Long id) {
		return carroRepo.findById(id).orElse(null);
	}

	public Carro save(Carro carro) {
		Carro nuevoCarro = carroRepo.save(carro);
		return nuevoCarro;
	}

	public List<Carro> byUsuarioId(Long usuarioId) {
		return carroRepo.findByUsuarioId(usuarioId);
	}
}
