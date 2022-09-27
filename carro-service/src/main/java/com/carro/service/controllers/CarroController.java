package com.carro.service.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carro.service.entidades.Carro;
import com.carro.service.servicios.CarroService;

@RestController
@RequestMapping("/carro")
public class CarroController {

	private CarroService carroService;

	public CarroController(CarroService carroService) {
		this.carroService = carroService;
	}

	@GetMapping
	public ResponseEntity<List<Carro>> listarCarros() {
		List<Carro> carros = carroService.getAll();
		if (carros.isEmpty()) {
			System.out.println("no hay carros");
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carros);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Carro> obtenerCarro(@PathVariable("id") Long id) {
		Carro carro = carroService.getCarroById(id);
		if (carro == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(carro);
		}
	}

	@PostMapping
	public ResponseEntity<Carro> guardarCarro(@RequestBody Carro carro) {
		Carro nuevoCarro = carroService.save(carro);
		return ResponseEntity.ok(nuevoCarro);

	}

	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Carro>> listarCarrosPorUsuarioId(@PathVariable("usuarioId") Long usuarioId) {
		List<Carro> carros = carroService.byUsuarioId(usuarioId);
		if (carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carros);
	}
}
