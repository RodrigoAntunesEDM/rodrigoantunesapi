package br.edu.infnet.rodrigoantunesapi.exceptions;

public class PorteiroNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PorteiroNaoEncontradoException(String messagem) {
		super(messagem);
	}
	
}
