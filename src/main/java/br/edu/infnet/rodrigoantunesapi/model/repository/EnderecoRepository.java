package br.edu.infnet.rodrigoantunesapi.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.infnet.rodrigoantunesapi.model.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
	//Metodo busca por Cep
	Optional<Endereco> findByCep(String cep);
}
