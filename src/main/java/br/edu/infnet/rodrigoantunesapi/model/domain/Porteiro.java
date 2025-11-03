package br.edu.infnet.rodrigoantunesapi.model.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;


@Entity
@Table(
	    name = "porteiro",
	    uniqueConstraints = @UniqueConstraint(columnNames = {"cpf"}))

public class Porteiro extends Pessoa{

	@NotBlank (message="A matrícula do porteiro é obrigatória.")
	@Column(unique = true, nullable = false)
	String matricula;
	
	@NotNull(message="A data de admissão do porteito é obrigatória.")
    @PastOrPresent(message = "A data de admissão não pode ser futura.")
	LocalDate dataAdmissao;
	
	Boolean ativo=true;
	Boolean excluido=false;
	
	//Relacionamento 1 porteiro para 1 endereço
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id", nullable = true)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@Valid
	Endereco endereco;
	
	//Relacionamento 1 porteiro para muitos objetos
	@OneToMany(mappedBy = "porteiro", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<Objeto> objetos = new ArrayList<>();
	
	
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
	public Boolean getExcluido() {
		return excluido;
	}
	public void setExcluido(Boolean excluido) {
		this.excluido = excluido;
	}

}
