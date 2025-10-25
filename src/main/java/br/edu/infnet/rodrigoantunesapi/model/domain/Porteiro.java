package br.edu.infnet.rodrigoantunesapi.model.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


@Entity
public class Porteiro extends Pessoa{

	@NotNull (message="A matrícula do porteiro é obrigatória.")
	String matricula;
	
	@NotNull(message="A data de admissão do porteito é obrigatória.")
	LocalDate dataAdmissao;
	
	Boolean ativo=true;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id", nullable = true)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@Valid
	Endereco endereco;
	
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}
