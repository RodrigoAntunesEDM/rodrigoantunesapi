package br.edu.infnet.rodrigoantunesapi.model.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Objeto {

	private Integer id;
	
	private String codigo;
	private LocalDateTime dataEntrada;
	private String porteiro;
	private String apartamento;
	private LocalDateTime dataRetirada;
	private String retirante;
	private Boolean retirado = false;
	private Boolean excluido = false;
	
	
	
	@Override
	public String toString() {
		
		String mensagem;
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		/*
		mensagem= "ID: " + getId();
		
		mensagem=mensagem + " - Objeto: "+ getCodigo() +" - Apartemento: "+ getApartamento() + " - Entregue: ";
		
		if (getRetirado() ){
			
			mensagem= mensagem + "Sim";
			
			mensagem= mensagem + " - Data: " + getDataRetirada().format(formato);
			
			
		} else {mensagem= mensagem + "Não";}
		*/
		

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
	
	public String getPorteiro() {
		return porteiro;
	}
	
	public void setPorteiro(String porteiro) {
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
	
	public String getRetirante() {
		return retirante;
	}
	
	public void setRetirante(String retirante) {
		this.retirante = retirante;
	}
	
	public Boolean getRetirado() {
		return retirado;
	}
	
	public void setRetirado(Boolean retirado) {
		this.retirado = retirado;
	
		//if (this.retirado = true) {
		//	this.dataRetirada = LocalDateTime.now();}
		//else
		// 	{this.dataRetirada = null;}
		
	}
	
	
	public Boolean getExcluido() {
		return excluido;
	}
	
	public void setExcluido(Boolean excluido) {
		this.excluido = excluido;
	}
	
}
