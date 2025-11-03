package br.edu.infnet.rodrigoantunesapi.model.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

@Entity
public class Objeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "O código de identificação do objeto é obrigatório.")
	private String codigo;
	
	@NotNull(message="Data da entrada do objeto é obrigatória.")
    @PastOrPresent(message = "A data de entrada não pode ser futura.")
	private LocalDateTime dataEntrada;

	@NotBlank(message = "O apartamento da entrega é obrigatório.")
    @Pattern(
            regexp = "^[A-Z][0-9]{3}$",
            message = "O apartamento deve seguir o formato Letra e 3 dígitos, ex: B101."
        )
	private String apartamento;
	
	private LocalDateTime dataRetirada;
	
	//Muitos objetos para um porteiro
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "porteiro_id", nullable = false)
    @NotNull(message = "O porteiro é obrigatório.")
    private Porteiro porteiro;

    //Muitos objetos para um morador
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "morador_id", nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "objetos"})
    private Morador morador;
	

	private Boolean retirado = false;
	private Boolean excluido = false;
	
	@Override
	public String toString() {
		
		String mensagem;
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		mensagem= String.format(
		        "ID: %04d | Objeto: %-8s | Apartamento: %-4s | Porteiro: %-20s | Entregue: %-3s | Data: %-16s | Morador: %-20s  ",
		        id,
		        codigo,
		        apartamento,
		        porteiro.getNome(),
		        retirado ? "Sim" : "Não",
		        retirado ? dataRetirada.format(formato) : "",
	        	morador != null ? morador.getNome() : ""	
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
	}
	
	public Boolean getExcluido() {
		return excluido;
	}
	
	public void setExcluido(Boolean excluido) {
		this.excluido = excluido;
	}
	
}
