
package br.edu.infnet.rodrigoantunesapi.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Contato {

	//public enum TipoContato {
	//    CELULAR,
	//    FIXO,
	//    EMAIL
	//}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull(message= "O tipo de contato é obrigatório.")
	@Enumerated(EnumType.STRING)
	TipoContato tipoContato;
	
	@NotNull(message="O contato é obrigatório.")
	String contato;
	
	// Relacionamento muitos contatos para 1 morador
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "morador_id", nullable=false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "contato"})
	private Morador morador;
    
   
	public Morador getMorador() {
		return morador;
	}
	public void setMorador(Morador morador) {
		this.morador = morador;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

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
