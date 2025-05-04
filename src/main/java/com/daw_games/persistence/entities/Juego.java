package com.daw_games.persistence.entities;

import java.time.LocalDate;

import com.daw_games.persistence.entities.enums.TipoJuego;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "juego")
public class Juego {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	private String genero;
	private String plataforma;

	private Double precio;

	@Column(name = "fecha_lanzamiento")
	private LocalDate fechaLanzamiento;

	private Boolean completado;

	private Integer descargas;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private TipoJuego tipo;

	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public LocalDate getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public Boolean getCompletado() {
		return completado;
	}

	public void setCompletado(Boolean completado) {
		this.completado = completado;
	}

	public Integer getDescargas() {
		return descargas;
	}

	public void setDescargas(Integer descargas) {
		this.descargas = descargas;
	}

	public TipoJuego getTipo() {
		return tipo;
	}

	public void setTipo(TipoJuego tipo) {
		this.tipo = tipo;
	}
}
