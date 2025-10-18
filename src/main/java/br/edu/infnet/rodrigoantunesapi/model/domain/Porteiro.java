package br.edu.infnet.rodrigoantunesapi.model.domain;

import java.time.LocalDate;

public class Porteiro extends Pessoa{

	String matricula;
	LocalDate dataAdmissao;
	Boolean ativo=true;
	
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
