package br.edu.infnet.rodrigoantunesapi.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class Morador extends Pessoa{
	
	String apartamento;
	Boolean ativo=true;

    //@OneToMany(mappedBy = "morador", cascade = CascadeType.ALL, orphanRemoval = true)
	//private List<Contato> listacontato;
    
    @Transient
    private Contato contato;
    
	public String getApartamento() {
		return apartamento;
	}
	
	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}
