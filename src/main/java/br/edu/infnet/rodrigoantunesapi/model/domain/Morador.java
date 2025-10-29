package br.edu.infnet.rodrigoantunesapi.model.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
	    name = "morador",
	    uniqueConstraints = @UniqueConstraint(columnNames = {"cpf"}))
	    
public class Morador extends Pessoa{
	
	@NotNull(message="O apartamento do morador é obrigatório.")
	String apartamento;
	
	Boolean ativo=true;
	Boolean excluido=false;

	//Relacionamento 1 morador para muitos contatos
    @OneToMany(mappedBy = "morador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("morador")
    @Valid
	private List<Contato> contato = new ArrayList<>();
    
    //Relacionamento 1 morador para muitos objetos
    @OneToMany(mappedBy = "morador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Objeto> objetos = new ArrayList<>();
    
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

	public Boolean getExcluido() {
		return excluido;
	}

	public void setExcluido(Boolean excluido) {
		this.excluido = excluido;
	}
	
}
