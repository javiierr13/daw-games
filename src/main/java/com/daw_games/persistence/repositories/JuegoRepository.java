package com.daw_games.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw_games.persistence.entities.Juego;
import com.daw_games.persistence.entities.enums.TipoJuego;

public interface JuegoRepository extends JpaRepository<Juego, Long> {

	// Buscar por nombre
	List<Juego> findByNombreContaining(String nombre);

	// Buscar por género
	List<Juego> findByGenero(String genero);

	// Buscar por plataforma
	List<Juego> findByPlataforma(String plataforma);

	// Buscar por tipo (usando enum TipoJuego)
	List<Juego> findByTipo(TipoJuego tipo);

	// Buscar por rango de precio
	List<Juego> findByPrecioBetween(Double precioMin, Double precioMax);

	// Buscar por juegos con más de un número de descargas
	List<Juego> findByDescargasGreaterThan(Integer descargas);
}
