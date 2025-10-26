package br.edu.infnet.rodrigoantunesapi.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.infnet.rodrigoantunesapi.model.domain.Objeto;


public interface ObjetoRepository extends JpaRepository<Objeto, Integer>{
	//Metodo busca por codigo
	Optional<Objeto> findByCodigo(String codigo);
}
