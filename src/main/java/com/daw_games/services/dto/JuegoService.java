package com.daw_games.services.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw_games.persistence.entities.Juego;
import com.daw_games.persistence.entities.enums.TipoJuego;
import com.daw_games.persistence.repositories.JuegoRepository;
import com.daw_games.services.exception.JuegoNotFoundException;

@Service
public class JuegoService {

	private final JuegoRepository juegoRepository;

	@Autowired
	public JuegoService(JuegoRepository juegoRepository) {
		this.juegoRepository = juegoRepository;
	}

	// Crear un nuevo juego
	public Juego crearJuego(Juego juego) {
		if (juego.getFechaLanzamiento() == null) {
			juego.setFechaLanzamiento(LocalDate.now());
		}
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
		return juegoRepository.findById(id).map(existing -> {
			existing.setNombre(juego.getNombre());
			existing.setGenero(juego.getGenero());
			existing.setPlataforma(juego.getPlataforma());
			existing.setPrecio(juego.getPrecio());
			existing.setFechaLanzamiento(juego.getFechaLanzamiento());
			existing.setDescargas(juego.getDescargas());
			existing.setTipo(juego.getTipo());
			// No se modifica el campo "completado"
			return juegoRepository.save(existing);
		}).orElseThrow(() -> new JuegoNotFoundException("Juego con ID " + id + " no encontrado."));
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
		return juegoRepository.findById(id).map(juego -> {
			juego.setCompletado(true);
			return juegoRepository.save(juego);
		}).orElseThrow(() -> new JuegoNotFoundException("Juego con ID " + id + " no encontrado."));
	}

	// Desmarcar como completado
	public Juego desmarcarComoCompletado(Long id) {
		return juegoRepository.findById(id).map(juego -> {
			juego.setCompletado(false);
			return juegoRepository.save(juego);
		}).orElseThrow(() -> new JuegoNotFoundException("Juego con ID " + id + " no encontrado."));
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
		try {
			TipoJuego tipoJuego = TipoJuego.valueOf(tipo.toUpperCase());
			return juegoRepository.findByTipo(tipoJuego);
		} catch (IllegalArgumentException e) {
			return List.of();
		}
	}

	// Buscar juegos por rango de precios
	public List<Juego> buscarPorPrecio(Double precioMin, Double precioMax) {
		return juegoRepository.findByPrecioBetween(precioMin, precioMax);
	}

	// Buscar juegos con más de X descargas
	public List<Juego> buscarPopulares(Integer descargas) {
		return juegoRepository.findByDescargasGreaterThan(descargas);
	}
}
