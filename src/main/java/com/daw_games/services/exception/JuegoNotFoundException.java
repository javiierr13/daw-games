package com.daw_games.services.exception;

public class JuegoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public JuegoNotFoundException(String message) {
		super(message);
	}
}
