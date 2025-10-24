package br.edu.infnet.rodrigoantunesapi.model.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class Morador extends Pessoa{
	
	String apartamento;
	Boolean ativo=true;

    //@OneToMany(mappedBy = "morador", cascade = CascadeType.ALL, orphanRemoval = true)
	//private List<Contato> listacontato;
    
    //@OneToMany(mappedBy = "morador", cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
	private List<Contato> contato;
    
	public String getApartamento() {
		return apartamento;
	}
	
	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}


	public List<Contato> getContato() {
		return contato;
	}

	public void setContato(List<Contato> contato) {
		this.contato = contato;
	}

	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}
