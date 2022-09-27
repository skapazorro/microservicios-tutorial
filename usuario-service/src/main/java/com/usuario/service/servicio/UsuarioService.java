package com.usuario.service.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.feingclients.CarroFeingClient;
import com.usuario.service.feingclients.MotoFeingClient;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.repositorio.UsuarioRepo;

@Service
public class UsuarioService {

	private UsuarioRepo usuarioRepo;
	private RestTemplate restTemplate;
	private CarroFeingClient carroFeing;
	private MotoFeingClient motoFeing;

	public UsuarioService(UsuarioRepo usuarioRepo, CarroFeingClient carroFeing, MotoFeingClient motoFeing,
			RestTemplate restTemplate) {
		this.usuarioRepo = usuarioRepo;
		this.carroFeing = carroFeing;
		this.restTemplate = restTemplate;
		this.motoFeing = motoFeing;
	}

	public List<Carro> getCarros(Long usuarioId) {
		List<Carro> carros = restTemplate.getForObject("http://localhost:8002/carro/usuario/" + usuarioId, List.class);
		return carros;
	}

	public List<Moto> getMotos(Long usuarioId) {
		List<Moto> motos = restTemplate.getForObject("http://localhost:8003/moto/usuario/" + usuarioId, List.class);
		return motos;
	}

	public Carro saveCarro(Long usuarioId, Carro carro) {
		carro.setUsuarioId(usuarioId);
		Carro nuevoCarro = carroFeing.save(carro);

		return nuevoCarro;
	}

	public Moto saveMoto(Long usuarioId, Moto moto) {
		moto.setUsuarioId(usuarioId);
		Moto nuevaMoto = motoFeing.save(moto);

		return nuevaMoto;
	}

	public List<Usuario> getAll() {
		return usuarioRepo.findAll();
	}

	public Usuario getUsuarioById(Long id) {
		return usuarioRepo.findById(id).orElse(null);
	}

	public Usuario save(Usuario usuario) {
		Usuario nuevoUsuario = usuarioRepo.save(usuario);
		return nuevoUsuario;
	}

	public Map<String, Object> getUsuarioAndVehiculos(Long usuarioId) {
		Map<String, Object> res = new HashMap<>();
		Usuario usuario = usuarioRepo.findById(usuarioId).orElse(null);
		if (usuario == null) {
			res.put("Mensaje", "El usuario no existe");
			return res;
		}

		res.put("Usuario", usuario);
		List<Carro> carros = carroFeing.getCarros(usuarioId);
		if (carros != null) {
			if (carros.isEmpty()) {
				res.put("Carros", "el usuario no tiene carros");
			} else {
				res.put("Carros", carros);
			}
		} else {
			res.put("Carros", "el usuario no tiene carros");
		}

		List<Moto> motos = motoFeing.getMotos(usuarioId);
		if (motos != null) {
			if (motos.isEmpty()) {
				res.put("Motos", "el usuario no tiene motos");
			} else {
				res.put("Motos", motos);
			}
		} else {
			res.put("Motos", "el usuario no tiene motos");
		}
		return res;
	}
}
