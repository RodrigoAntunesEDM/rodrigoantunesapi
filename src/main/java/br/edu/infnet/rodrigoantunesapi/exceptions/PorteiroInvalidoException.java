package br.edu.infnet.rodrigoantunesapi.exceptions;

public class PorteiroInvalidoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PorteiroInvalidoException(String messagem) {
		super(messagem);
	}
	
}
