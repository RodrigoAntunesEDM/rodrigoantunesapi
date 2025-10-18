package br.edu.infnet.rodrigoantunesapi.exceptions;

public class ObjetoNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ObjetoNaoEncontradoException(String messagem) {
		super(messagem);
	}
	
}
