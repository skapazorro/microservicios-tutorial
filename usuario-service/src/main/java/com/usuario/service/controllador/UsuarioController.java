package com.usuario.service.controllador;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.servicio.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		List<Usuario> usuarios = usuarioService.getAll();
		if (usuarios.isEmpty()) {
			System.out.println("no hay usuarios");
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(usuarios);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") Long id) {
		Usuario usuario = usuarioService.getUsuarioById(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(usuario);
		}
	}

	@PostMapping
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
		Usuario nuevoUsuario = usuarioService.save(usuario);
		return ResponseEntity.ok(nuevoUsuario);
	}

	@GetMapping("/carros/{usuarioId}")
	public ResponseEntity<List<Carro>> listarCarrosPorUsuarioId(@PathVariable("usuarioId") Long usuarioId) {
		Usuario usuario = usuarioService.getUsuarioById(usuarioId);
		if (usuario == null) {
			System.out.println("no hay un usuario con ese id");
			return ResponseEntity.noContent().build();
		}
		List<Carro> carros = usuarioService.getCarros(usuarioId);
		return ResponseEntity.ok(carros);
	}

	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>> listarMotosPorUsuarioId(@PathVariable("usuarioId") Long usuarioId) {
		Usuario usuario = usuarioService.getUsuarioById(usuarioId);
		if (usuario == null) {
			System.out.println("no hay un usuario con ese id");
			return ResponseEntity.noContent().build();
		}
		List<Moto> motos = usuarioService.getMotos(usuarioId);
		return ResponseEntity.ok(motos);
	}

	@PostMapping("/carro/{usuarioId}")
	public ResponseEntity<Carro> guardarCarro(@RequestBody Carro carro, @PathVariable("usuarioId") Long usuarioId) {
		Carro nuevoCarro = usuarioService.saveCarro(usuarioId, carro);
		return ResponseEntity.ok(nuevoCarro);
	}

	@PostMapping("/moto/{usuarioId}")
	public ResponseEntity<Moto> guardarMoto(@RequestBody Moto moto, @PathVariable("usuarioId") Long usuarioId) {
		Moto nuevaMoto = usuarioService.saveMoto(usuarioId, moto);
		return ResponseEntity.ok(nuevaMoto);
	}
	
	@GetMapping("/todos/{usuarioId}")
	public ResponseEntity<Map<String,Object>> listarTodosLosVehiculos(@PathVariable("usuarioId") Long usuarioId){
		Map<String, Object> res = usuarioService.getUsuarioAndVehiculos(usuarioId);
		return ResponseEntity.ok(res);
	}
}
