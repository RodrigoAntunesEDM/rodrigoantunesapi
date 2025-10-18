package br.edu.infnet.rodrigoantunesapi.model.domain;

public class Contato {

	public enum TipoContato {
	    CELULAR,
	    FIXO,
	    EMAIL
	}
	
	TipoContato tipoContato;
	String contato;
	
	
	public TipoContato getTipoContato() {
		return tipoContato;
	}
	public void setTipoContato(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	
	
}
