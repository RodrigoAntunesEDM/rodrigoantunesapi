package br.edu.infnet.rodrigoantunesapi.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.infnet.rodrigoantunesapi.model.domain.Objeto;


public interface ObjetoRepository extends JpaRepository<Objeto, Integer>{
	//Metodo busca por codigo
	Optional<Objeto> findByCodigo(String codigo);
	
	// Busca todos os objetos por apartamento 
    List<Objeto> findByApartamento(String apartamento);
    
    // Busca objetos não entregues (retirado = false) de um determinado apartamento
    List<Objeto> findByApartamentoAndRetiradoFalse(String apartamento);
    
    // Busca objetos entregues (retirado = true) de um apartamento, ordenados por dataRetirada Desc
    List<Objeto> findByApartamentoAndRetiradoTrueOrderByDataRetiradaDesc(String apartamento);
    
}
