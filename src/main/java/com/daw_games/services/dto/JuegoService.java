package com.daw_games.services.dto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw_games.persistence.entities.Juego;
import com.daw_games.persistence.entities.enums.TipoJuego;
import com.daw_games.persistence.repositories.JuegoRepository;

@Service
public class JuegoService {

	private final JuegoRepository juegoRepository;

	@Autowired
	public JuegoService(JuegoRepository juegoRepository) {
		this.juegoRepository = juegoRepository;
	}

	// Crear un nuevo juego
	public Juego crearJuego(Juego juego) {
		return juegoRepository.save(juego);
	}

	// Obtener todos los juegos
	public List<Juego> obtenerTodosLosJuegos() {
		return juegoRepository.findAll();
	}

	// Obtener un juego por ID
	public Optional<Juego> obtenerJuegoPorId(Long id) {
		return juegoRepository.findById(id);
	}

	// Modificar un juego
	public Juego modificarJuego(Long id, Juego juego) {
		if (juegoRepository.existsById(id)) {
			juego.setId(id);
			return juegoRepository.save(juego);
		}
		return null;
	}

	// Borrar un juego
	public boolean borrarJuego(Long id) {
		if (juegoRepository.existsById(id)) {
			juegoRepository.deleteById(id);
			return true;
		}
		return false;
	}

	// Marcar como completado
	public Juego marcarComoCompletado(Long id) {
		Optional<Juego> juegoOpt = juegoRepository.findById(id);
		if (juegoOpt.isPresent()) {
			Juego juego = juegoOpt.get();
			juego.setCompletado(true);
			return juegoRepository.save(juego);
		}
		return null;
	}

	// Buscar juegos por nombre
	public List<Juego> buscarPorNombre(String nombre) {
		return juegoRepository.findByNombreContaining(nombre);
	}

	// Buscar juegos por género
	public List<Juego> buscarPorGenero(String genero) {
		return juegoRepository.findByGenero(genero);
	}

	// Buscar juegos por plataforma
	public List<Juego> buscarPorPlataforma(String plataforma) {
		return juegoRepository.findByPlataforma(plataforma);
	}

	// Buscar juegos por tipo
	public List<Juego> buscarPorTipo(String tipo) {
		// Convertir el String a TipoJuego
		try {
			TipoJuego tipoJuego = TipoJuego.valueOf(tipo.toUpperCase());
			return juegoRepository.findByTipo(tipoJuego);
		} catch (IllegalArgumentException e) {
			return List.of();
		}
	}

	// Buscar juegos por precio
	public List<Juego> buscarPorPrecio(Double precioMin, Double precioMax) {
		return juegoRepository.findByPrecioBetween(precioMin, precioMax);
	}

	// Buscar juegos populares (más de 1000 descargas)
	public List<Juego> buscarPopulares(Integer descargas) {
		return juegoRepository.findByDescargasGreaterThan(descargas);
	}
}
