package br.edu.infnet.rodrigoantunesapi.model.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

@Entity
public class Objeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="Código de identificaçao do objeto é obrigatório.")
	private String codigo;
	
	@NotNull(message="Data da entrada do objeto é obrigatória.")
	private LocalDateTime dataEntrada;
	//private String porteiro;
	
	@NotNull (message = "Apartamento para entrega é obrigatório.")
	private String apartamento;
	
	private LocalDateTime dataRetirada;
	
	@Transient
	private Porteiro porteiro;
	
	
	//private String morador;
	@Transient
	private Morador morador;
	
	private Boolean retirado = false;
	private Boolean excluido = false;
	
	
	
	@Override
	public String toString() {
		
		String mensagem;
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		

		mensagem= String.format(
		        "ID: %04d | Objeto: %-8s | Apartamento: %-4s | Entregue: %-3s | Data: %-16s",
		        id,
		        codigo,
		        apartamento,
		        retirado ? "Sim" : "Não",
		        retirado ? dataRetirada.format(formato) : ""
		        );
	
		
		return  mensagem;
		
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}
	
	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	
	public Porteiro getPorteiro() {
		return porteiro;
	}
	
	public void setPorteiro(Porteiro porteiro) {
		this.porteiro = porteiro;
	}
	
	public String getApartamento() {
		return apartamento;
	}
	
	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}
	
	public LocalDateTime getDataRetirada() {
		return dataRetirada;
	}
	
	public void setDataRetirada(LocalDateTime dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	
	public Morador getMorador() {
		return morador;
	}
	
	public void setMorador(Morador morador) {
		this.morador = morador;
	}
	
	public Boolean getRetirado() {
		return retirado;
	}
	
	public void setRetirado(Boolean retirado) {
		this.retirado = retirado;
	
		//if (this.retirado = true) {
		//	setDataRetirada(LocalDateTime.now());
		//	}
		//else
		//{setDataRetirada(null);}
		
	}
	
	
	public Boolean getExcluido() {
		return excluido;
	}
	
	public void setExcluido(Boolean excluido) {
		this.excluido = excluido;
	}
	
}
