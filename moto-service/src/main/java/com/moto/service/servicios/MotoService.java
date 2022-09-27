package com.moto.service.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moto.service.entidades.Moto;
import com.moto.service.repositorios.MotoRepo;


@Service
public class MotoService {

	private MotoRepo motoRepo;

	public MotoService(MotoRepo motoRepo) {
		this.motoRepo = motoRepo;
	}

	public List<Moto> getAll() {
		return motoRepo.findAll();
	}

	public Moto getMotoById(Long id) {
		return motoRepo.findById(id).orElse(null);
	}

	public Moto save(Moto moto) {
		Moto nuevaMoto = motoRepo.save(moto);
		return nuevaMoto;
	}

	public List<Moto> byUsuarioId(Long usuarioId) {
		return motoRepo.findByUsuarioId(usuarioId);
	}
}
