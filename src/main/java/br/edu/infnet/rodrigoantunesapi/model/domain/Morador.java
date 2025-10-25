package br.edu.infnet.rodrigoantunesapi.model.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Entity
public class Morador extends Pessoa{
	
	@NotNull(message="O apartamento do morador é obrigatório.")
	String apartamento;
	
	Boolean ativo=true;

    @OneToMany(mappedBy = "morador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Valid
	private List<Contato> contato = new ArrayList<Contato>();
    
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
