package com.daw_games.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw_games.persistence.entities.Juego;
import com.daw_games.persistence.entities.enums.TipoJuego;
import com.daw_games.services.dto.JuegoService;
import com.daw_games.services.exception.JuegoNotFoundException;

@RestController
@RequestMapping("/juegos")
public class JuegoControllers {

	@Autowired
	private JuegoService juegoService;

	@GetMapping
	public ResponseEntity<List<Juego>> listarTodos() {
		return ResponseEntity.ok(juegoService.obtenerTodosLosJuegos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
		Optional<Juego> juegoOpt = juegoService.obtenerJuegoPorId(id);
		if (juegoOpt.isPresent()) {
			return ResponseEntity.ok(juegoOpt.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Juego no encontrado");
		}
	}

	@PostMapping
	public ResponseEntity<Juego> crear(@RequestBody Juego juego) {
		return ResponseEntity.status(HttpStatus.CREATED).body(juegoService.crearJuego(juego));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Juego juego) {
		try {
			return ResponseEntity.ok(juegoService.modificarJuego(id, juego));
		} catch (JuegoNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> borrar(@PathVariable Long id) {
		if (juegoService.borrarJuego(id)) {
			return ResponseEntity.ok("Juego borrado correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID no encontrado");
		}
	}

	@PutMapping("/completar/{id}")
	public ResponseEntity<?> marcarComoCompletado(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(juegoService.marcarComoCompletado(id));
		} catch (JuegoNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PutMapping("/descompletar/{id}")
	public ResponseEntity<?> desmarcarComoCompletado(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(juegoService.desmarcarComoCompletado(id));
		} catch (JuegoNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping("/genero/{genero}")
	public ResponseEntity<List<Juego>> buscarPorGenero(@PathVariable String genero) {
		return ResponseEntity.ok(juegoService.buscarPorGenero(genero));
	}

	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<List<Juego>> buscarPorNombre(@PathVariable String nombre) {
		return ResponseEntity.ok(juegoService.buscarPorNombre(nombre));
	}

	@GetMapping("/plataforma/{plataforma}")
	public ResponseEntity<List<Juego>> buscarPorPlataforma(@PathVariable String plataforma) {
		return ResponseEntity.ok(juegoService.buscarPorPlataforma(plataforma));
	}

	@GetMapping("/expansiones")
	public ResponseEntity<List<Juego>> obtenerExpansiones() {
		return ResponseEntity.ok(juegoService.buscarPorTipo(TipoJuego.EXPANSION.name()));
	}

	@GetMapping("/dlcs")
	public ResponseEntity<List<Juego>> obtenerDLCs() {
		return ResponseEntity.ok(juegoService.buscarPorTipo(TipoJuego.DLC.name()));
	}

	@GetMapping("/base")
	public ResponseEntity<List<Juego>> obtenerBase() {
		return ResponseEntity.ok(juegoService.buscarPorTipo(TipoJuego.BASE.name()));
	}

	@GetMapping("/precio")
	public ResponseEntity<List<Juego>> buscarPorPrecio(@RequestParam Double min, @RequestParam Double max) {
		return ResponseEntity.ok(juegoService.buscarPorPrecio(min, max));
	}

	@GetMapping("/populares")
	public ResponseEntity<List<Juego>> buscarPopulares() {
		return ResponseEntity.ok(juegoService.buscarPopulares(1000));
	}
}
