
package br.edu.infnet.rodrigoantunesapi.model.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

//@Entity
public class Contato {

	public enum TipoContato {
	    CELULAR,
	    FIXO,
	    EMAIL
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;
	
	TipoContato tipoContato;
	String contato;
	
	 // Relacionamento: muitos contatos -> 1 morador
    @ManyToOne
    @JoinColumn(name = "morador_id")
    //private Morador morador;
    
	

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
